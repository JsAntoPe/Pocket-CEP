package com.siddhiApi.dao;

import com.siddhiApi.entity.Stream;

public interface StreamDAO {
    void createSchema(Stream stream);

    Stream getSchema(String stream);
}
