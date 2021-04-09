Kafka:
Kafka producer and consumer.
LibraryEventProducer: @Component, KafkaTemplate<Integer, String>

Controller:
@RestController, @Valid, @ControllerAdvice, @ExceptionHandler(MethodArgumentNotValidException.class)

Integration test:
EmbeddedKafka, EmbeddedKafkaBroker, RestTemplate, HttpEntity.

Unit test:
Junit, Mockito(@InjectMocks, @Mock, @Spy), SettableListenableFuture.
Unit tests in the controller: @WebMvcTest, @AutoConfigureMockMvc, @MockBean, mockMvc.perform(post())