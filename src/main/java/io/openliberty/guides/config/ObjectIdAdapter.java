package io.openliberty.guides.config;

import jakarta.json.bind.adapter.JsonbAdapter;
import org.bson.types.ObjectId;

public class ObjectIdAdapter implements JsonbAdapter<ObjectId, String> {

    @Override
    public String adaptToJson(ObjectId objectId) throws Exception {
        return objectId != null ? objectId.toHexString() : null;
    }

    @Override
    public ObjectId adaptFromJson(String id) throws Exception {
        return id != null && !id.isEmpty() ? new ObjectId(id) : null;
    }
}