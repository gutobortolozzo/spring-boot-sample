package com.sample;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;

@Configuration
@EnableMongoRepositories
public class MongoConfiguration extends AbstractMongoConfiguration {

	private static MongodExecutable mongodExecutable;
	
	@Override
	protected String getDatabaseName() {
		return "service-sample";
	}

	@Override
	public Mongo mongo() throws Exception {
		return new MongoClient("localhost", 12345);
	}
	
	public static void start() throws Exception{
		if(mongodExecutable != null) return;
		
		MongodStarter starter = MongodStarter.getDefaultInstance();
		
		IMongodConfig mongodConfig = new MongodConfigBuilder()
			.version(Version.Main.PRODUCTION)
			.net(new Net(12345, Network.localhostIsIPv6()))
			.build();
		
		mongodExecutable = starter.prepare(mongodConfig);
        mongodExecutable.start();
	}
	
	public static void stop(){
		mongodExecutable.stop();
	}
}
