package com.siddhiApi.services;

import com.siddhiApi.entity.Stream;
import net.minidev.json.JSONObject;

public interface StreamService {
    public void createStream(Stream stream);

    public Stream getStream(String stream);
}
