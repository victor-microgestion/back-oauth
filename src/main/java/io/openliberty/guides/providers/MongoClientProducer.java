package io.openliberty.guides.providers;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;

@ApplicationScoped
public class MongoClientProducer {

    String connectionString = "mongodb+srv://admin:admin@java-login.dv96h.mongodb.net/?retryWrites=true&w=majority&appName=java-login";

    //@ConfigProperty(name = "User")
    String dbName = "User";

    @Produces
    public MongoClient createMongoClient() {
        return MongoClients.create(
                MongoClientSettings.builder()
                        .applyConnectionString(new ConnectionString(connectionString))
                        .build()
        );
    }

    @Produces
    public MongoDatabase createDatabase(MongoClient mongoClient) {
        return mongoClient.getDatabase(dbName);
    }

    public void closeMongoClient(@Disposes MongoClient mongoClient) {
        mongoClient.close();
    }
}