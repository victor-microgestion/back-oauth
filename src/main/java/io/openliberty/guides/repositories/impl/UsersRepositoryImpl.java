package io.openliberty.guides.repositories.impl;

import org.bson.conversions.Bson;

import com.mongodb.client.MongoClient;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;

import io.openliberty.guides.repositories.UsersRepositoryCustom;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class UsersRepositoryImpl implements UsersRepositoryCustom {
    @Inject
    MongoClient mongoClient;

    @Override
    public boolean updateByEmail(String email, String name, String emailNuevo, String provider, String role) {
        System.out.println("LLEGUE al metodo updateByEmail");
        Bson filter = Filters.eq("email", email);
        Bson update = Updates.combine(
                Updates.set("name", name),
                Updates.set("email", emailNuevo),
                Updates.set("provider", provider),
                Updates.set("role", role));

        UpdateResult result = mongoClient.getDatabase("User")
                .getCollection("Users")
                .updateOne(filter, update);

        return result.getModifiedCount() > 0;
    }
}
