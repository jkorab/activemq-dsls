package com.ameliant.activemq.dsl;

/**
 * @author jkorab
 */
public class PolicyMapDefinition {

    private final DestinationPolicyDefinition destinationPolicyDefinition;
    private PolicyEntriesDefinition policyEntriesDefinition;

    PolicyMapDefinition(DestinationPolicyDefinition destinationPolicyDefinition) {
        assert (destinationPolicyDefinition != null);
        this.destinationPolicyDefinition = destinationPolicyDefinition;
    }


    public PolicyEntriesDefinition policyEntries() {
        policyEntriesDefinition = new PolicyEntriesDefinition(this);
        return policyEntriesDefinition;
    }

    public DestinationPolicyDefinition end() {
        return destinationPolicyDefinition;
    }

    PolicyEntriesDefinition getPolicyEntriesDefinition() {
        return policyEntriesDefinition;
    }
}
