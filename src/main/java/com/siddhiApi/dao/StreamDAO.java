package com.siddhiApi.dao;

import com.siddhiApi.entity.Stream;

public interface StreamDAO {
    void createStream(Stream stream);

    Stream getStream(String stream);
}
