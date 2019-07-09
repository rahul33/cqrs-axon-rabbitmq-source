package com.cqrs.axon.sample.handler;

import com.cqrs.axon.sample.event.ApplicationSubmittedEvent;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
public class ApplicationEventHandler {

    @EventHandler
    public void on(ApplicationSubmittedEvent event) {
        System.out.println("Id in source : " + event.getId());
        System.out.println("Name in source : " + event.getName());
        System.out.println("Dob in source: " + event.getDob());
        System.out.println("SSN in source: " + event.getSsn());
    }
}
