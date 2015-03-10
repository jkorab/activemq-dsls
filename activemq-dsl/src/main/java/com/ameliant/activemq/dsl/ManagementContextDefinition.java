package com.ameliant.activemq.dsl;

/**
 * @author jkorab
 */
public class ManagementContextDefinition {

    private BrokerDefinition brokerDefinition;
    private ManagementContext1Definition managementContext1Definition;

    ManagementContextDefinition(BrokerDefinition brokerDefinition) {
        assert (brokerDefinition != null);
        this.brokerDefinition = brokerDefinition;
    }

    public ManagementContext1Definition managementContext() {
        managementContext1Definition = new ManagementContext1Definition(this);
        return managementContext1Definition;
    }

    public BrokerDefinition end() {
        return brokerDefinition;
    }

    ManagementContext1Definition getManagementContext1Definition() {
        return managementContext1Definition;
    }
}
