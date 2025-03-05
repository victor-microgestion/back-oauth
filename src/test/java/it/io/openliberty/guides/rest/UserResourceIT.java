package it.io.openliberty.guides.rest;

import static org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import io.restassured.http.ContentType;
import jakarta.ws.rs.core.Response;

@Testcontainers
public class UserResourceIT {

    private static final String BASE_URL = "http://localhost:9080/api/users";

    private static final MongoDBContainer mongoContainer = 
        new MongoDBContainer(DockerImageName.parse("mongo:6.0"))
            .withExposedPorts(27017);

    @BeforeAll
    public static void setUp() {
        mongoContainer.start();
        System.setProperty("mongo.connectionString", mongoContainer.getReplicaSetUrl());
    }

    @AfterAll
    public static void tearDown() {
        mongoContainer.stop();
    }

    @Test
    public void testCreateAndGetUser() {
        Map<String, Object> newUser = new HashMap<>();
        newUser.put("name", "John Doe");
        newUser.put("email", "john.doe@example.com");
        newUser.put("provider", "google");
        newUser.put("role", "user");

        // Crear usuario
        given()
            .contentType(ContentType.JSON)
            .body(newUser)
        .when()
            .post(BASE_URL)
        .then()
            .statusCode(Response.Status.CREATED.getStatusCode());

        // Obtener lista de usuarios y verificar que se creó
        String responseBody = given()
            .contentType(ContentType.JSON)
        .when()
            .get(BASE_URL)
        .then()
            .statusCode(Response.Status.OK.getStatusCode())
            .extract().asString();

        assertTrue(responseBody.contains("john.doe@example.com"), "El usuario no fue encontrado en la lista.");
    }

    @Test
    public void testDeleteUser() {
        // Crear usuario primero
        Map<String, Object> newUser = new HashMap<>();
        newUser.put("name", "Jane Doe");
        newUser.put("email", "jane.doe@example.com");
        newUser.put("provider", "github");
        newUser.put("role", "admin");

        given()
            .contentType(ContentType.JSON)
            .body(newUser)
        .when()
            .post(BASE_URL)
        .then()
            .statusCode(Response.Status.CREATED.getStatusCode());

        // Eliminar usuario
        given()
            .contentType(ContentType.JSON)
        .when()
            .delete(BASE_URL + "/jane.doe@example.com")
        .then()
            .statusCode(Response.Status.OK.getStatusCode());

        // Intentar obtener usuario eliminado (debe dar 404)
        given()
            .contentType(ContentType.JSON)
        .when()
            .get(BASE_URL + "/jane.doe@example.com")
        .then()
            .statusCode(Response.Status.NOT_FOUND.getStatusCode());
    }
}
