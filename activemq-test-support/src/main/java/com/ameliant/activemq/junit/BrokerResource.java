package com.ameliant.activemq.junit;

import com.ameliant.activemq.dsl.BrokerContext;
import com.ameliant.activemq.dsl.BrokerDefinition;
import com.ameliant.activemq.dsl.TransportConnectorDefinition;
import com.ameliant.activemq.dsl.TransportConnectorsDefinition;
import org.apache.commons.lang.Validate;
import org.junit.rules.ExternalResource;

import java.net.URI;
import java.util.Collection;

/**
 * @author jkorab
 */
public class BrokerResource extends ExternalResource {
    protected final BrokerDefinition brokerDefinition;
    private BrokerContext brokerContext;

    public BrokerResource(BrokerDefinition brokerDefinition) {
        Validate.notNull(brokerDefinition, "brokerDefinition is null");
        this.brokerDefinition = brokerDefinition;
        this.brokerContext = new BrokerContext(brokerDefinition);
    }

    @Override
    protected void before() throws Throwable {
        brokerContext.start();
    }

    @Override
    protected void after() {
        brokerContext.stop();
    }


    public ProxyDefinition withProxied(String transportConnectorName) {
        Validate.notEmpty(transportConnectorName, "transportConnectorName is empty");
        ProxyDefinition proxyDefinition = new ProxyDefinition(this, transportConnectorName);
        return proxyDefinition;
    }

    BrokerDefinition getBrokerDefinition() {
        return brokerDefinition;
    }

    /**
     * Gets the <code>tcp://</code> connection URI to the named transportConnector.
     * Intended for use as input into an {@link org.apache.activemq.ActiveMQConnectionFactory}.
     * @param transportConnectorName The name of the transportConnector.
     * @return The connection URI.
     * @throws java.lang.IllegalStateException if no transportConnector is defined with the given name, or if the scheme
     * on that transportConnector is anything other than <code>tcp</code> or <code>nio</code>.
     */
    public String getTcpConnectionUri(String transportConnectorName) {
        Validate.notEmpty(transportConnectorName, "transportConnectorName is empty");

        TransportConnectorsDefinition transportConnectorsDefinition = brokerDefinition.getTransportConnectorsDefinition();
        if (transportConnectorsDefinition == null) {
            throw new IllegalStateException("No transportConnectors defined");
        }

        Collection<TransportConnectorDefinition> tcDefinitions = transportConnectorsDefinition.getTransportConnectorDefinitions();
        String retval = null;
        for (TransportConnectorDefinition tcDefinition: tcDefinitions) {
            if (tcDefinition.getName().equals(transportConnectorName)) {
                URI tcUri = URI.create(tcDefinition.getUri());
                assertSchemeIn(tcUri.getScheme(), "tcp", "nio");
                retval = "tcp://localhost:" + tcUri.getPort();
                break;
            }
        }
        if (retval == null) {
            throw new IllegalStateException("No transportConnector found named " + transportConnectorName);
        }
        return retval;
    }

    protected void assertSchemeIn(String scheme, String ... expectedSchemes) {
        boolean found = false;
        for (String expectedScheme : expectedSchemes) {
            if (scheme.equals(expectedScheme)) {
                found = true;
                break;
            }
        }
        if (!found) {
            StringBuilder sb = new StringBuilder("Unexpected scheme " + scheme + ". Expected ");

            for (int i = 0; i < expectedSchemes.length; i++) {
                if (i > 0) {
                    sb.append(", ");
                }
                sb.append(expectedSchemes[i]);
            }
            throw new IllegalArgumentException(sb.toString());
        }

    }
}
