package com.ameliant.activemq.dsl;

import org.apache.commons.lang.Validate;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author jkorab
 */
public class NetworkConnectorsDefinition {

    private final BrokerDefinition brokerDefinition;
    private Map<String, NetworkConnectorDefinition> networkConnectorDefinitionMap = new TreeMap<>();

    NetworkConnectorsDefinition(BrokerDefinition brokerDefinition) {
        assert (brokerDefinition != null);
        this.brokerDefinition = brokerDefinition;
    }

    public NetworkConnectorDefinition networkConnector(String name, String uri) {
        Validate.notEmpty(name, "name is empty");
        Validate.notEmpty(uri, "uri is empty");

        if (networkConnectorDefinitionMap.containsKey(name)) {
            throw new IllegalArgumentException("Network connector named " + name + "already defined");
        }
        NetworkConnectorDefinition transportConnectorDefinition = new NetworkConnectorDefinition(this, name, uri);
        networkConnectorDefinitionMap.put(name, transportConnectorDefinition);
        return transportConnectorDefinition;
    }

    public BrokerDefinition end() {
        return brokerDefinition;
    }

    Collection<NetworkConnectorDefinition> getNetworkConnectorDefinitions() {
        return networkConnectorDefinitionMap.values();
    }
}
