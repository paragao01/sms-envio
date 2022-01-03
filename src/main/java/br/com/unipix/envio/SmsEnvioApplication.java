package br.com.unipix.envio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.unipix.envio.model.ProcessoCampanha;
import br.com.unipix.envio.service.ProcessoCampanhaService;


@SpringBootApplication
public class SmsEnvioApplication implements CommandLineRunner {

	Logger log = LoggerFactory.getLogger(SmsEnvioApplication.class);
	
	@Autowired
	private ProcessoCampanhaService processoService;
	
	public static void main(String[] args) {
		SpringApplication.run(SmsEnvioApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		int threadId = 3;
		while (true) {
			switch (threadId % 2) {
			case 1:
				ProcessoCampanha minuto = processoService.obterUltimoProcesso();
				if(minuto != null) {
					processoService.enviarCampanhaAgendada(minuto);
					//processoService.enviarCampanhaAgendadaFalha(minuto);
					processoService.deletarProcessos(minuto);
				}
				break;
			case 0:
				processoService.enviarCampanha();
				break;
			}
			threadId++;
			if (threadId == 200) {
				threadId = 3;
			}
			Thread.sleep(100);
		}
	}

}
