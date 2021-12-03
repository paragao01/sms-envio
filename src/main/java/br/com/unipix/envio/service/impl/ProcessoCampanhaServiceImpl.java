package br.com.unipix.envio.service.impl;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.data.querydsl.QSort;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;

import br.com.unipix.envio.enumeration.StatusCampanhaEnum;
import br.com.unipix.envio.enumeration.StatusProcessoEnum;
import br.com.unipix.envio.enumeration.StatusSmsEnum;
import br.com.unipix.envio.model.CampanhaKafka;
import br.com.unipix.envio.model.ProcessoCampanha;
import br.com.unipix.envio.mongo.model.CampanhaDashboard;
import br.com.unipix.envio.mongo.model.CampanhaDocument;
import br.com.unipix.envio.mongo.repository.CampanhaDashboarRepository;
import br.com.unipix.envio.mongo.repository.CampanhaDocumentRepository;
import br.com.unipix.envio.repository.ProcessoCampanhaRepository;
import br.com.unipix.envio.service.ProcessoCampanhaService;

@Service
public class ProcessoCampanhaServiceImpl implements ProcessoCampanhaService{
	

	Logger log = LoggerFactory.getLogger(ProcessoCampanhaService.class);

	@Autowired
	private ProcessoCampanhaRepository repository;
	
	@Autowired
	private CampanhaDocumentRepository campanhaMongoRepository;
	
	@Autowired
	private CampanhaDashboarRepository campanhaDashboardRepository;
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	@Autowired
	private Gson jsonConverter;
	
	@Override
	public ProcessoCampanha obterUltimoProcesso() {
		ProcessoCampanha minuto = repository.obterUltimoProcesso();
		LocalDateTime dataAtual = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
		if(minuto != null) {
			if(minuto.getMinuto().compareTo(dataAtual)<=0) {
				return minuto;
			}
		}
		return null;
	}
	
	@Transactional
	public void enviarCampanhaAgendada(ProcessoCampanha processo) {
		LocalDateTime dataInicioProcesso = LocalDateTime.now();
		LocalDateTime data = processo.getMinuto();
		ExecutorService executorService = Executors.newFixedThreadPool(24);
		List<CampanhaDashboard> campanhasAgendadas = campanhaDashboardRepository.obterCampanhasAgendadas(data, StatusCampanhaEnum.AGENDADO.getName());
		if(campanhasAgendadas != null) {
			campanhasAgendadas.stream().parallel().forEachOrdered(campanha -> executorService.execute(() -> {
				@SuppressWarnings("deprecation")
				Pageable page = new QPageRequest(0, 1000, QSort.unsorted());
				try {
					campanhaDashboardRepository.updateStatusCampanha(campanha.getId(), StatusCampanhaEnum.ENVIANDO);
					campanhaDashboardRepository.updateStatusAgendado(data, StatusProcessoEnum.PROCESSANDO, campanha.getId());
					List<CampanhaDocument> smsAgendados = new ArrayList<>();
					Boolean x = true;
					while(x) {
						smsAgendados = campanhaMongoRepository.
								buscarSmsAgendado(data, campanha.getIdCampanhaSql(),StatusSmsEnum.ESPERA.getName(), page);
						Boolean pausa = pausa(campanha.getIdCampanhaSql());
						if(smsAgendados.size() <= 0 || pausa) {
							log.info(String.format("Envio de campanha: %s, de id %d finalizado", campanha.getNomeCampanha(), campanha.getIdCampanhaSql()));
							x = false;
						}
						log.info(String.format("Processando envio agendado da campanha: %s, de id %d", campanha.getNomeCampanha(), campanha.getIdCampanhaSql()));
						campanhaMongoRepository.updateStatusSms(data, StatusSmsEnum.EM_PROCESSAMENTO);
						send(smsAgendados, campanha.getIdCampanhaSql());	
						page = page.next();
					}
					campanhaDashboardRepository.updateStatusAgendado(data, StatusProcessoEnum.PROCESSADO, campanha.getId());
				}catch(Exception e){
					System.out.println(e.getStackTrace());
				}
			}));
			try {
				executorService.shutdown();
				executorService.awaitTermination(3000, TimeUnit.MINUTES);
			}catch(Exception e) {
				Thread.currentThread().interrupt();
			}
		}
		
		LocalDateTime dataFimProcesso = LocalDateTime.now();
		ProcessoCampanha novoMinuto = ProcessoCampanha.builder()
				.minuto(data.plusMinutes(1))
				.status(StatusProcessoEnum.NAO_PROCESSADO)
				.build();
		processo.setFimProcesso(dataFimProcesso);
		processo.setInicioProcesso(dataInicioProcesso);
		processo.setStatus(StatusProcessoEnum.PROCESSADO);
		repository.saveAll(Arrays.asList(novoMinuto, processo));	
	}
	
	public Boolean pausa(Long idCampanhaSql) {
		CampanhaDashboard campanha = campanhaDashboardRepository.obterCampanhaPausada(idCampanhaSql);
		if(campanha != null) {
			return true;
		}
		return false;
	}

	
	@Transactional
	public void send(List<CampanhaDocument> documentos, Long idCampanhaSql) {
		List<CampanhaKafka> json = new ArrayList<>();
		documentos.forEach(documento -> {
				CampanhaKafka kafka = CampanhaKafka.builder().
						smsId(documento.getId()).
						produtoId(documento.getProduto().getId()).
						campanhaId(documento.getIdCampanhaSql()).
						numero(documento.getNumero()).
						mensagem(documento.getMensagem()).
						build();
				json.add(kafka);
		});
		if(json.size() > 0) {
			kafkaTemplate.send("sms", jsonConverter.toJson(json));			
			campanhaMongoRepository.updateStatusSms(documentos, StatusSmsEnum.ENVIANDO);
		}
		System.out.println("Enviando "+documentos.size()+" sms");
	}

	@Override
	@Transactional
	public void enviarCampanha() {
		
		ExecutorService executorService = Executors.newFixedThreadPool(24);
		List<CampanhaDashboard> campanhas = campanhaDashboardRepository.obterCampanhas(StatusCampanhaEnum.ENVIANDO.getName());
		if(campanhas != null) {
			campanhas.stream().parallel().forEachOrdered(campanha -> executorService.execute(() -> {
				campanhaDashboardRepository.updateStatusCampanha(campanha.getId(), StatusCampanhaEnum.ENVIADO);
				@SuppressWarnings("deprecation")
				Pageable page = new QPageRequest(0, 1000, QSort.unsorted());
				log.info(String.format("Processando envio da campanha: %s, de id %d", campanha.getNomeCampanha(), campanha.getIdCampanhaSql()));
				
				try {
					List<CampanhaDocument> smsAgendados = new ArrayList<>();
					Boolean x = true;
					while(x) {
						smsAgendados = campanhaMongoRepository.
								buscarSms(campanha.getIdCampanhaSql(),StatusSmsEnum.AGUARDANDO_PROCESSAMENTO.getName(), page);
						
						if(smsAgendados.size() <= 0) {
							log.info(String.format("Envio de campanha: %s, de id %d Finalizado", campanha.getNomeCampanha(), campanha.getIdCampanhaSql()));
							x = false;
							break;
						}
						log.info(String.format("Processando envio da campanha: %s, de id %d, pagina %d", campanha.getNomeCampanha(), campanha.getIdCampanhaSql(), page.getPageNumber()));
						campanhaMongoRepository.updateStatusSms(smsAgendados,StatusSmsEnum.EM_PROCESSAMENTO);
						send(smsAgendados, campanha.getIdCampanhaSql());	
						page = page.next();
					}
				}catch(Exception e){
					System.out.println(e.getStackTrace());
				}
		}));
		try {
			executorService.shutdown();
			executorService.awaitTermination(3000, TimeUnit.MINUTES);
		}catch(Exception e) {
			Thread.currentThread().interrupt();
		}
	}
			
	}
}
