/*
 * Copyright (c) 2010-2014. Axon Framework
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.cqrs.axon.sample.converter;

import org.axonframework.eventhandling.EventMessage;
import org.springframework.messaging.Message;

/**
 * Interface describing a mechanism that converts AMQP Messages from an Axon Messages and vice versa.
 *
 * @author Allard Buijze
 * @since 2.0
 */
public interface SpringMessageEventMessageConverter {

    /**
     * Creates an AMQPMessage from given {@code eventMessage}.
     *
     * @param eventMessage The EventMessage to create the AMQP Message from
     * @return an AMQP Message containing the data and characteristics of the Message to send to the AMQP Message
     * Broker.
     */
    Message<?> toSpringMessage(EventMessage<?> eventMessage);

    /**
     * Reconstruct an EventMessage from the given spring message. The returned optional
     * resolves to a message if the given input parameters represented a correct event message.
     *
     * @param message spring message
     * @return The Event Message to publish on the local event processors
     */
    EventMessage<?> toEventMessage(Message<?> message);
}
