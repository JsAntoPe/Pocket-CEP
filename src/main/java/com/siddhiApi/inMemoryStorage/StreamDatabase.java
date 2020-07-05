package com.siddhiApi.inMemoryStorage;

import com.siddhiApi.entity.Stream;
import com.siddhiApi.exceptions.NotFoundException;

import java.util.HashMap;
import java.util.Map;

public class StreamDatabase {
    private Map<String, Stream> streams;
    private static StreamDatabase streamDatabase;

    public static StreamDatabase getStreamDatabase() {
        if(streamDatabase == null){
            streamDatabase = new StreamDatabase();
        }
        return streamDatabase;
    }

    private StreamDatabase(){
        streams = new HashMap<>();
    }

    public Map<String, Stream> getStreams() {
        return streams;
    }

    public void setStreams(Map<String, Stream> streams) {
        this.streams = streams;
    }

    public static void setStreamDatabase(StreamDatabase streamDatabase) {
        StreamDatabase.streamDatabase = streamDatabase;
    }

    public void addStream(Stream stream) {
        streams.put(stream.getStreamID(), stream);
    }

    public Stream getStream(String name) throws NotFoundException {
        if (!streams.containsKey(name)){
            throw new NotFoundException("A stream with that ID does not exist.");
        }
        return streams.get(name);
    }
}
