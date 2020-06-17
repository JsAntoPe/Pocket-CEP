package com.siddhiApi.entity;

import net.minidev.json.JSONObject;

public class Stream {
    private String streamID;
    private JSONObject jsonSchema;


    public Stream(String streamID, JSONObject jsonSchema) {
        this.streamID = streamID;
        this.jsonSchema = jsonSchema;
    }

    public String getStreamID() {
        return streamID;
    }

    public void setStreamID(String streamID) {
        this.streamID = streamID;
    }

    public JSONObject getJsonSchema() {
        return jsonSchema;
    }

    public void setJsonSchema(JSONObject jsonSchema) {
        this.jsonSchema = jsonSchema;
    }
}
