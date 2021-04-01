package com.kafka.libraryeventsproducer.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kafka.libraryeventsproducer.domain.LibraryEvent;
import com.kafka.libraryeventsproducer.producer.LibraryEventProducer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

@RestController
@Slf4j
@Api
@RequestMapping("/v1/libraryevent")
public class LibraryEventsController {

    @Autowired
    LibraryEventProducer libraryEventProducer;

    @PostMapping
    @ApiOperation("postlibraryevent")
    public ResponseEntity<LibraryEvent> postLibraryEvent(@RequestBody LibraryEvent libraryEvent) throws JsonProcessingException, ExecutionException, InterruptedException, TimeoutException {

        log.info("before producer");
        //libraryEventProducer.sendLibraryEvent(libraryEvent);
        //libraryEventProducer.sendLibraryEventSync(libraryEvent);
        libraryEventProducer.sendLibraryEventWithProducerRecord(libraryEvent);
        log.info("after producer");
        return ResponseEntity.status(HttpStatus.CREATED).body(libraryEvent);
    }

}
