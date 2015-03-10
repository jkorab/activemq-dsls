package com.ameliant.activemq.dsl;

/**
 * @author jkorab
 */
public class BrokerDefinition {

    private final String brokerName;
    private boolean useJmx = false;
    private boolean persistent = true;
    private DestinationPolicyDefinition destinationPolicyDefinition;
    private ManagementContextDefinition managementContextDefinition;
    private NetworkConnectorsDefinition networkConnectorsDefinition;
    private TransportConnectorsDefinition transportConnectorsDefinition;
    private PluginsDefinition pluginsDefinition;

    BrokerDefinition(String brokerName) {
        this.brokerName = brokerName;
    }

    public BrokerDefinition persistent(boolean persistent) {
        this.persistent = persistent;
        return this;
    }

    public BrokerDefinition useJmx(boolean useJmx) {
        this.useJmx = useJmx;
        return this;
    }

    public ManagementContextDefinition managementContext() {
        managementContextDefinition = new ManagementContextDefinition(this);
        return managementContextDefinition;
    }

    public NetworkConnectorsDefinition networkConnectors() {
        networkConnectorsDefinition = new NetworkConnectorsDefinition(this);
        return networkConnectorsDefinition;
    }

    public TransportConnectorsDefinition transportConnectors() {
        transportConnectorsDefinition = new TransportConnectorsDefinition(this);
        return transportConnectorsDefinition;
    }

    boolean isPersistent() {
        return persistent;
    }

    boolean isUseJmx() {
        return useJmx;
    }

    String getBrokerName() {
        return brokerName;
    }

    ManagementContextDefinition getManagementContextDefinition() {
        return managementContextDefinition;
    }

    NetworkConnectorsDefinition getNetworkConnectorsDefinition() {
        return networkConnectorsDefinition;
    }

    public TransportConnectorsDefinition getTransportConnectorsDefinition() {
        return transportConnectorsDefinition;
    }

    public PluginsDefinition plugins() {
        pluginsDefinition = new PluginsDefinition(this);
        return pluginsDefinition;
    }

    PluginsDefinition getPluginsDefinition() {
        return pluginsDefinition;
    }

    public DestinationPolicyDefinition destinationPolicy() {
        destinationPolicyDefinition = new DestinationPolicyDefinition(this);
        return destinationPolicyDefinition;
    }

    DestinationPolicyDefinition getDestinationPolicyDefinition() {
        return destinationPolicyDefinition;
    }
}
