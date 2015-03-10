package com.ameliant.activemq.dsl;

/**
 * @author jkorab
 */
public class NetworkConnectorDefinition {

    private final NetworkConnectorsDefinition networkConnectorsDefinition;
    private final String name;
    private final String uri;
    private Integer networkTTL;

    NetworkConnectorDefinition(NetworkConnectorsDefinition networkConnectorsDefinition, String name, String uri) {
        assert (networkConnectorsDefinition != null);
        assert (name != null);
        assert (uri != null);

        this.uri = uri;
        this.name = name;
        this.networkConnectorsDefinition = networkConnectorsDefinition;
    }

    public NetworkConnectorDefinition networkTTL(int networkTTL) {
        this.networkTTL = networkTTL;
        return this;
    }


    public DestinationsDefinition staticallyIncludedDestinations() {
        DestinationsDefinition destinationsDefinition = new DestinationsDefinition(this);
        return destinationsDefinition;
    }

    public NetworkConnectorsDefinition end() {
        return networkConnectorsDefinition;
    }

    public String getName() {
        return name;
    }

    String getUri() {
        return uri;
    }
}
