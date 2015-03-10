package com.ameliant.activemq.dsl;

/**
 * @author jkorab
 */
public class PluginsDefinition {

    private final BrokerDefinition brokerDefinition;
    private SimpleAuthenticationPluginDefinition simpleAuthenticationPluginDefinition;

    PluginsDefinition(BrokerDefinition brokerDefinition) {
        assert (brokerDefinition != null);
        this.brokerDefinition = brokerDefinition;
    }


    public SimpleAuthenticationPluginDefinition simpleAuthenticationPlugin() {
        simpleAuthenticationPluginDefinition = new SimpleAuthenticationPluginDefinition(this);
        return simpleAuthenticationPluginDefinition;
    }

    public BrokerDefinition end() {
        return brokerDefinition;
    }

    SimpleAuthenticationPluginDefinition getSimpleAuthenticationPluginDefinition() {
        return simpleAuthenticationPluginDefinition;
    }
}
