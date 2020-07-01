package com.siddhiApi.dao;


import com.siddhiApi.entity.Stream;

public interface StreamDAO {
    public void createSchema(Stream stream);

    public Stream getSchema(String stream);
}
