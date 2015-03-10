package com.ameliant.activemq.dsl;

import org.apache.commons.lang.Validate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jkorab
 */
public class DestinationsDefinition {

    private final NetworkConnectorDefinition networkConnectorDefinition;
    private final List<String> topics = new ArrayList<>();
    private final List<String> queues = new ArrayList<>();

    DestinationsDefinition(NetworkConnectorDefinition networkConnectorDefinition) {
        assert (networkConnectorDefinition != null);
        this.networkConnectorDefinition = networkConnectorDefinition;
    }

    public DestinationsDefinition topic(String topicName) {
        Validate.notEmpty(topicName, "topicName is empty");

        if (topics.contains(topicName)) {
            throw new IllegalArgumentException("Topic " + topicName + "already added to networkConnector " + networkConnectorDefinition.getName());
        }
        topics.add(topicName);
        return this;
    }

    public DestinationsDefinition queue(String queueName) {
        Validate.notEmpty(queueName, "queueName is empty");

        if (queues.contains(queueName)) {
            throw new IllegalArgumentException("Queue " + queueName + "already added to networkConnector " + networkConnectorDefinition.getName());
        }
        queues.add(queueName);
        return this;
    }

    public NetworkConnectorDefinition end() {
        return networkConnectorDefinition;
    }

}
