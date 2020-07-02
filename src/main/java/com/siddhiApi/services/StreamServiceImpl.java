package com.siddhiApi.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.siddhiApi.dao.SiddhiDAO;
import com.siddhiApi.dao.SiddhiDAOImpl;
import com.siddhiApi.dao.StreamDAOImpl;
import com.siddhiApi.entity.Stream;
import com.siddhiApi.dao.StreamDAO;
import com.siddhiApi.util.Parsers;
import org.everit.json.schema.ValidationException;
import org.json.JSONObject;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class StreamServiceImpl implements StreamService{

    Logger logger = LoggerFactory.getLogger(StreamServiceImpl.class);

    @Autowired
    private final StreamDAO streamDao = new StreamDAOImpl();

    @Autowired
    private final SiddhiDAO siddhiDAO = new SiddhiDAOImpl();

    @Override
    public void createStream(Stream stream) {
        streamDao.createStream(stream);
    }

    @Override
    public Stream getStream(String stream) {
        return streamDao.getStream(stream);
    }

    @Override
    public void sendEvent(String stream, Object event) throws ValidationException, JsonProcessingException {
        JSONObject streamSchema = streamDao.getStream(stream).getJsonSchema();

        ObjectMapper mapper = new ObjectMapper();
        JSONObject eventSchema = new JSONObject(mapper.writeValueAsString(event));

        Schema schema = SchemaLoader.load(streamSchema);
        schema.validate(eventSchema);

        Object[] eventParsed = Parsers.JsonObjectToObjectArray(eventSchema);
        for (Object object: eventParsed){
            logger.info("Property on event already parsed: " + object);
        }
        siddhiDAO.sendEvent(stream, eventParsed);
    }
}
