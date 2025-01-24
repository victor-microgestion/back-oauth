package io.openliberty.guides.repositories;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import io.openliberty.guides.models.Oportunidad;
import io.openliberty.guides.models.Seguimiento;
import io.openliberty.guides.models.common.Cotizacion;
import io.openliberty.guides.models.common.Fechas;
import io.openliberty.guides.models.common.ItemFacturable;
import io.openliberty.guides.models.common.Propuesta;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class OportunidadRepository {

    @Inject
    MongoDatabase database;

    private MongoCollection<Document> getCollection() {
        return database.getCollection("oportunidades");
    }

    // CREATE
    public void save(Oportunidad oportunidad) {
        Document document = new Document()
                .append("codigo", oportunidad.getCodigo())
                .append("estado", oportunidad.getEstado())
                .append("vendedor", oportunidad.getVendedor())
                .append("fechas", oportunidad.getFechas())
                .append("propuestas", oportunidad.getPropuestas())
                .append("cotizaciones", oportunidad.getCotizaciones())
                .append("itemsFacturables", oportunidad.getItemsFacturables())
                .append("workflow", oportunidad.getWorkflow())
                .append("seguimiento", oportunidad.getSeguimiento())
                .append("estadoColores", oportunidad.getEstadoColores());

        getCollection().insertOne(document);
    }

    // READ ALL
    public List<Oportunidad> findAll() {
        return getCollection().find()
                .map(document -> mapDocumentToOportunidad(document))
                .into(new ArrayList<>());
    }

    // READ BY ID
    public Oportunidad findByCodigo(String codigo) {
        Document query = new Document("codigo", codigo);
        Document document = getCollection().find(query).first();

        return document != null ? mapDocumentToOportunidad(document) : null;
    }

    // UPDATE
    public boolean update(String codigo, Oportunidad updatedOportunidad) {
        Document query = new Document("codigo", codigo);
        Document updatedDocument = new Document()
                .append("estado", updatedOportunidad.getEstado())
                .append("vendedor", updatedOportunidad.getVendedor())
                .append("fechas", updatedOportunidad.getFechas())
                .append("propuestas", updatedOportunidad.getPropuestas())
                .append("cotizaciones", updatedOportunidad.getCotizaciones())
                .append("itemsFacturables", updatedOportunidad.getItemsFacturables())
                .append("workflow", updatedOportunidad.getWorkflow())
                .append("seguimiento", updatedOportunidad.getSeguimiento())
                .append("estadoColores", updatedOportunidad.getEstadoColores());

        Document updateOperation = new Document("$set", updatedDocument);
        return getCollection().updateOne(query, updateOperation).getMatchedCount() > 0;
    }

    // DELETE
    public boolean deleteByCodigo(String codigo) {
        Document query = new Document("codigo", codigo);
        return getCollection().deleteOne(query).getDeletedCount() > 0;
    }

    // Helper: Mapear Document a Oportunidad
    private Oportunidad mapDocumentToOportunidad(Document document) {
        Oportunidad oportunidad = new Oportunidad();
        oportunidad.setCodigo(document.getString("codigo"));
        oportunidad.setEstado(document.getString("estado"));
        oportunidad.setVendedor(document.getString("vendedor"));
        oportunidad.setFechas((Fechas) document.get("fechas"));
        oportunidad.setPropuestas((List<Propuesta>) document.get("propuestas"));
        oportunidad.setCotizaciones((List<Cotizacion>) document.get("cotizaciones"));
        oportunidad.setItemsFacturables((List<ItemFacturable>) document.get("itemsFacturables"));
        oportunidad.setWorkflow(document.getString("workflow"));
        oportunidad.setSeguimiento((Seguimiento) document.get("seguimiento"));
        oportunidad.setEstadoColores(document.getString("estadoColores"));

        return oportunidad;
    }
}
