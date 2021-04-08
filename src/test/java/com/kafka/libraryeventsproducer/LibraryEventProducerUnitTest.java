package com.kafka.libraryeventsproducer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.libraryeventsproducer.domain.Book;
import com.kafka.libraryeventsproducer.domain.LibraryEvent;
import com.kafka.libraryeventsproducer.producer.LibraryEventProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.util.concurrent.SettableListenableFuture;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LibraryEventProducerUnitTest {

    //the combination between @Mock and @InjectMocks is gonna inject
    // kafkaTemplate inside libraryEventProducer
    @Mock
    KafkaTemplate<Integer,String> kafkaTemplate;

    //Allows shorthand wrapping of field instances in an spy object.
    @Spy
    ObjectMapper objectMapper = new ObjectMapper();

    //you can use in order to create an instance od the class that is under test
    @InjectMocks
    LibraryEventProducer libraryEventProducer;

    @Test
    void sendLibraryEventWithProducerRecordFailure() {
        //given
        Book book = Book.builder()
                .bookId(123)
                .bookAuthor("Edevar")
                .bookName("Kafka using Spring Boot")
                .build();

        LibraryEvent libraryEvent = LibraryEvent.builder()
                .libraryEventId(null)
                .book(book)
                .build();

        //when
        SettableListenableFuture future = new SettableListenableFuture();
        //simulating the exception
        future.setException(new RuntimeException("Exception calling kafka"));
        when(kafkaTemplate.send(isA(ProducerRecord.class))).thenReturn(future);

        //then
        assertThrows(Exception.class, () -> libraryEventProducer.sendLibraryEventWithProducerRecord(libraryEvent).get());

    }
}
