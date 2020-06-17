package com.siddhiApi.dao;

import com.siddhiApi.entity.Stream;
import com.siddhiApi.structureDatabase.StreamDatabase;

import org.springframework.stereotype.Service;

@Service
public class StreamDAOImpl implements StreamDAO{

    private final StreamDatabase streamDatabase = StreamDatabase.getStreamDatabase();

    @Override
    public void createSchema(Stream stream) {
        streamDatabase.addStream(stream);
    }

    @Override
    public Stream getSchema(String stream) {
        return streamDatabase.getStream(stream);
    }
}
