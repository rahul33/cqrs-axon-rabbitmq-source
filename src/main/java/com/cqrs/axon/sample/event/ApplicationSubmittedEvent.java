package com.cqrs.axon.sample.event;

public class ApplicationSubmittedEvent {

    private final String id;
    private final String name;
    private final String dob;
    private final String ssn;

    public ApplicationSubmittedEvent(String id, String name, String dob, String ssn) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.ssn = ssn;
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
