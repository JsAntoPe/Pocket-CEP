package com.siddhiApi.services;

import com.siddhiApi.entity.Stream;
import com.siddhiApi.dao.StreamDAO;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class StreamServiceImpl implements StreamService{

    @Autowired
    private StreamDAO streamDao;

    @Override
    public void createStream(Stream stream) {
        streamDao.createSchema(stream);
    }

    @Override
    public Stream getStream(String stream) {
        return streamDao.getSchema(stream);
    }
}
