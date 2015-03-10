package com.ameliant.activemq.dsl;

import org.apache.commons.lang.Validate;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author jkorab
 */
public class TransportConnectorsDefinition {

    private final BrokerDefinition brokerDefinition;
    private Map<String, TransportConnectorDefinition> transportConnectorDefinitionMap = new TreeMap<>();

    TransportConnectorsDefinition(BrokerDefinition brokerDefinition) {
        assert (brokerDefinition != null);
        this.brokerDefinition = brokerDefinition;
    }

    public TransportConnectorDefinition transportConnector(String name, String uri) {
        Validate.notEmpty(name, "name is empty");
        Validate.notEmpty(uri, "uri is empty");

        if (transportConnectorDefinitionMap.containsKey(name)) {
            throw new IllegalArgumentException("Transport connector named " + name + "already defined");
        }
        TransportConnectorDefinition transportConnectorDefinition = new TransportConnectorDefinition(this, name, uri);
        transportConnectorDefinitionMap.put(name, transportConnectorDefinition);
        return transportConnectorDefinition;
    }

    public BrokerDefinition end() {
        return brokerDefinition;
    }

    public Collection<TransportConnectorDefinition> getTransportConnectorDefinitions() {
        return transportConnectorDefinitionMap.values();
    }
}
