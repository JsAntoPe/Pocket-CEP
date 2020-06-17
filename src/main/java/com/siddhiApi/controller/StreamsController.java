package com.siddhiApi.controller;

import com.siddhiApi.entity.Stream;
import com.siddhiApi.services.StreamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/streams")
public class StreamsController {

    @Autowired
    private StreamService streamService;

    @PostMapping()
    public void createStream(@RequestBody Stream stream){
        streamService.createStream(stream);
    }

    @GetMapping("/{name}")
    public Stream getStream(@PathVariable String name){
        return streamService.getStream(name);
    }

}
