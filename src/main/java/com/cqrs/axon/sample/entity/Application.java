package com.cqrs.axon.sample.entity;

import com.cqrs.axon.sample.command.SubmitApplicationCommand;
import com.cqrs.axon.sample.event.ApplicationSubmittedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;


@Aggregate
public class Application {

    @AggregateIdentifier
    private String id;
    private String name;
    private String dob;
    private String ssn;

    @CommandHandler
    public Application(SubmitApplicationCommand command) {
        apply(new ApplicationSubmittedEvent(command.getId(), command.getName(), command.getDob(), command.getSsn()));
    }

    @EventSourcingHandler
    public void on(ApplicationSubmittedEvent event) {
        this.id = event.getId();
        this.name = event.getName();
        this.dob = event.getDob();
        this.ssn = event.getSsn();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDob() {
        return dob;
    }

    public String getSsn() {
        return ssn;
    }
}
