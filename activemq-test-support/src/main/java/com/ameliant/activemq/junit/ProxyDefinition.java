package com.ameliant.activemq.junit;

/**
 * @author jkorab
 */
public class ProxyDefinition {
    private final BrokerResource brokerResource;
    private final String transportConnectorName;
    private Integer proxyPort;
    
    ProxyDefinition(BrokerResource brokerResource, String transportConnectorName) {
        assert (brokerResource != null);
        assert (transportConnectorName != null);

        this.brokerResource = brokerResource;
        this.transportConnectorName = transportConnectorName;
    }

    public ProxiedBrokerResource port(int proxyPort) {
        this.proxyPort = proxyPort;
        return new ProxiedBrokerResource(this);
    }

    BrokerResource getBrokerResource() {
        return brokerResource;
    }

    String getTransportConnectorName() {
        return transportConnectorName;
    }

    int getProxyPort() {
        assert (proxyPort != null);
        return proxyPort;
    }
}
