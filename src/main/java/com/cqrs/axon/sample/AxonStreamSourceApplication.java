package com.cqrs.axon.sample;

import com.cqrs.axon.sample.command.SubmitApplicationCommand;
import com.cqrs.axon.sample.inbound.AxonInboundChannelAdapter;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.GenericCommandMessage;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventsourcing.eventstore.EmbeddedEventStore;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.eventsourcing.eventstore.inmemory.InMemoryEventStorageEngine;
import org.axonframework.spring.config.AxonConfiguration;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;

import java.util.UUID;

@SpringBootApplication
@EnableBinding(Source.class)
public class AxonStreamSourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AxonStreamSourceApplication.class, args);
    }

    @Bean
    public EmbeddedEventStore eventStore(EventStorageEngine storageEngine, AxonConfiguration configuration) {
        return EmbeddedEventStore.builder()
                .storageEngine(storageEngine)
                .messageMonitor(configuration.messageMonitor(EventStore.class, "eventStore"))
                .build();
    }

    @Bean
    public EventStorageEngine storageEngine() {
        return new InMemoryEventStorageEngine();
    }


    @Bean
    public IntegrationFlow flow(EventBus eventBus) {
        return IntegrationFlows.from(new AxonInboundChannelAdapter(eventBus))
                .channel(Source.OUTPUT)
                .get();
    }


    @Bean
    CommandLineRunner run(CommandBus commandBus) {

        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                for (int i = 0; i < 200; i++) {
                    commandBus.dispatch(GenericCommandMessage.asCommandMessage(new SubmitApplicationCommand(UUID.randomUUID().toString(), "TestActor1", "01/01/1970", "121-222-1111")));
                    commandBus.dispatch(GenericCommandMessage.asCommandMessage(new SubmitApplicationCommand(UUID.randomUUID().toString(), "TestActor2", "01/02/1970", "111-222-3333")));
                    commandBus.dispatch(GenericCommandMessage.asCommandMessage(new SubmitApplicationCommand(UUID.randomUUID().toString(), "TestActor3", "01/03/1970", "111-444-1221")));
                }
            }
        };
    }

}
