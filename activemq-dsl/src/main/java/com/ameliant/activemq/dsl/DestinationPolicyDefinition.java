package com.ameliant.activemq.dsl;

/**
 * @author jkorab
 */
public class DestinationPolicyDefinition {

    private final BrokerDefinition brokerDefinition;
    private PolicyMapDefinition policyMapDefinition;

    DestinationPolicyDefinition(BrokerDefinition brokerDefinition) {
        assert (brokerDefinition != null);
        this.brokerDefinition = brokerDefinition;
    }

    public PolicyMapDefinition policyMap() {
        policyMapDefinition = new PolicyMapDefinition(this);
        return policyMapDefinition;
    }

    public BrokerDefinition end() {
        return brokerDefinition;
    }

    PolicyMapDefinition getPolicyMapDefinition() {
        return policyMapDefinition;
    }
}
