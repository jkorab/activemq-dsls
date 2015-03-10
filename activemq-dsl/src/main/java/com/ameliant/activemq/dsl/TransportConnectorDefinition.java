package com.ameliant.activemq.dsl;

/**
 * @author jkorab
 */
public class TransportConnectorDefinition {

    private final TransportConnectorsDefinition transportConnectorsDefinition;
    private final String name;
    private final String uri;

    public TransportConnectorDefinition(TransportConnectorsDefinition transportConnectorsDefinition, String name, String uri) {
        assert (transportConnectorsDefinition != null);
        assert (name != null);
        assert (uri != null);

        this.uri = uri;
        this.name = name;
        this.transportConnectorsDefinition = transportConnectorsDefinition;
    }

    public TransportConnectorsDefinition end() {
        return transportConnectorsDefinition;
    }

    public String getName() {
        return name;
    }

    public String getUri() {
        return uri;
    }
}
