package io.openliberty.guides.providers;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class MongoClientProducer {

    @Inject
    @ConfigProperty(name = "mongodb.uri")
    private String mongoUri;

    @Inject
    @ConfigProperty(name = "mongodb.database")
    private String dbName;

    @Produces
    public MongoClient createMongoClient() {
        return MongoClients.create(mongoUri);
    }

    @Produces
    public MongoDatabase createDatabase(MongoClient mongoClient) {
        return mongoClient.getDatabase(dbName);
    }
}
