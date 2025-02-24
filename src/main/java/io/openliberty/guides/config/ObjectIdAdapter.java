package io.openliberty.guides.config;

import jakarta.json.bind.adapter.JsonbAdapter;
import org.bson.types.ObjectId;

public class ObjectIdAdapter implements JsonbAdapter<String, String> {
    @Override
    public String adaptToJson(String id) throws Exception {
        return id;
    }

    @Override
    public String adaptFromJson(String json) throws Exception {
        if (json != null && ObjectId.isValid(json)) {
            return json;
        }
        return new ObjectId().toHexString();
    }
}