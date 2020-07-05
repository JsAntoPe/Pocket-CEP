package com.siddhiApi.dao;

import com.siddhiApi.entity.Stream;
import com.siddhiApi.exceptions.NotFoundException;

public interface StreamDAO {
    void createStream(Stream stream);

    Stream getStream(String stream) throws NotFoundException;
}
