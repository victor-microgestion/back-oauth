package io.openliberty.guides.repositories;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import io.openliberty.guides.models.User;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class UserRepository {

    @Inject
    MongoDatabase database;

    private MongoCollection<Document> getCollection() {
        return database.getCollection("users");
    }

    // CREATE
    public void save(User user) {
        Document document = new Document()
                .append("name", user.getName())
                .append("email", user.getEmail())
                .append("provider", user.getProvider())
                .append("role", user.getRole());

        getCollection().insertOne(document);
    }

    // READ ALL
    public List<User> findAll() {
        return getCollection().find()
                .map(document -> new User(
                        document.getString("name"),
                        document.getString("email"),
                        document.getString("provider"),
                        document.getString("role")
                )).into(new ArrayList<>());
    }

    // READ BY EMAIL
    public User findByEmail(String email) {
        Document query = new Document("email", email);
        Document userDocument = getCollection().find(query).first();

        if (userDocument != null) {
            return new User(
                    userDocument.getString("name"),
                    userDocument.getString("email"),
                    userDocument.getString("provider"),
                    userDocument.getString("role")
            );
        }
        return null;
    }

    // READ BY ID
    public User findById(String id) {
        Document query = new Document("_id", id);
        Document userDocument = getCollection().find(query).first();

        if (userDocument != null) {
            return new User(
                    userDocument.getString("name"),
                    userDocument.getString("email"),
                    userDocument.getString("provider"),
                    userDocument.getString("role")
            );
        }
        return null;
    }

    // UPDATE
    public boolean update(String email, User updatedUser) {
        Document query = new Document("email", email);
        Document updatedDocument = new Document()
                .append("name", updatedUser.getName())
                .append("email", updatedUser.getEmail())
                .append("provider", updatedUser.getProvider())
                .append("role", updatedUser.getRole());

        Document updateOperation = new Document("$set", updatedDocument);
        return getCollection().updateOne(query, updateOperation).getMatchedCount() > 0;
    }

    // DELETE
    public boolean deleteByEmail(String email) {
        Document query = new Document("email", email);
        return getCollection().deleteOne(query).getDeletedCount() > 0;
    }

    // DELETE BY ID
    public boolean deleteById(String id) {
        Document query = new Document("_id", id);
        return getCollection().deleteOne(query).getDeletedCount() > 0;
    }
}
