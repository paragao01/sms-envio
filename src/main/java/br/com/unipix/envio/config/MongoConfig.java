package br.com.unipix.envio.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;


@EnableMongoRepositories(basePackages = "br.com.unipix.envio.mongo.repository")
@Configuration
public class MongoConfig extends AbstractMongoClientConfiguration {
	
	@Value("${spring.data.mongodb.host}")
	public String mongoUri;
	
	@Value("${spring.data.mongodb.database}")
	public String mongoDataBase;
	
	@Override
	protected String getDatabaseName() {
		return mongoDataBase;
	}
	
	@Override
	protected void configureClientSettings(MongoClientSettings.Builder builder) {
	    builder.applyConnectionString(new ConnectionString("mongodb://"+mongoUri));
	}

}
