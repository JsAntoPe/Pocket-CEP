package com.siddhiApi.dao;

import com.siddhiApi.entity.EventStructure;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StreamStructureDao {
    void createStructure(String inputStreamName, String application);

    EventStructure getStructure(String inputStreamName);

    void removeStructure(String inputStreamName);
}
