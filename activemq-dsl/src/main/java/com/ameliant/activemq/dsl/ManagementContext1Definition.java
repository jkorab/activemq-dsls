package com.ameliant.activemq.dsl;

/**
 * @author jkorab
 */
public class ManagementContext1Definition {

    private final ManagementContextDefinition managementContextDefinition;
    private boolean createConnector = true;

    ManagementContext1Definition(ManagementContextDefinition managementContextDefinition) {
        assert (managementContextDefinition != null);
        this.managementContextDefinition = managementContextDefinition;
    }

    public ManagementContext1Definition createConnector(boolean createConnector) {
        this.createConnector = createConnector;
        return this;
    }

    public ManagementContextDefinition end() {
        return managementContextDefinition;
    }

    public boolean isCreateConnector() {
        return createConnector;
    }
}
