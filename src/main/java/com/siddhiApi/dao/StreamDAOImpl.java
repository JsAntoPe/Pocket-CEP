package com.siddhiApi.dao;

import com.siddhiApi.entity.Stream;
import com.siddhiApi.exceptions.NotFoundException;
import com.siddhiApi.inMemoryStorage.StreamDatabase;

import org.springframework.stereotype.Service;

@Service
public class StreamDAOImpl implements StreamDAO{

    private final StreamDatabase streamDatabase = StreamDatabase.getStreamDatabase();

    @Override
    public void createStream(Stream stream) {
        streamDatabase.addStream(stream);
    }

    @Override
    public Stream getStream(String stream) throws NotFoundException {
        return streamDatabase.getStream(stream);
    }
}
