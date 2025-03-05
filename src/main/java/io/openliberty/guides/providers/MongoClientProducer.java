package io.openliberty.guides.providers;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Singleton;

@ApplicationScoped
public class MongoClientProducer {

    private final MongoClient mongoClient;
    private final MongoDatabase mongoDatabase;

    @ConfigProperty(name = "mongo.connectionString")
    String connectionString;

    @ConfigProperty(name = "mongo.database")
    String dbName;

    public MongoClientProducer() {
        this.mongoClient = MongoClients.create(
                MongoClientSettings.builder()
                        .applyConnectionString(new ConnectionString("mongodb+srv://admin:admin@java-login.dv96h.mongodb.net/?retryWrites=true&w=majority&appName=java-login"))
                        .build()
        );
        this.mongoDatabase = mongoClient.getDatabase("User");
    }

    @Produces
    @Singleton
    public MongoClient getMongoClient() {
        return mongoClient;
    }

    @Produces
    @Singleton
    public MongoDatabase getDatabase() {
        return mongoDatabase;
    }

    @PreDestroy
    public void closeMongoClient() {
        mongoClient.close();
    }
}
