package com.ameliant.activemq.dsl;

/**
 * Base class for common attributes of queue and topic policyEntry elements.
 * @author jkorab
 */
public abstract class PolicyEntryDefinition {

    private final PolicyEntriesDefinition policyEntriesDefinition;
    private final String name;

    PolicyEntryDefinition(PolicyEntriesDefinition policyEntriesDefinition, String name) {
        assert (policyEntriesDefinition != null);
        assert (name != null);
        this.policyEntriesDefinition = policyEntriesDefinition;
        this.name = name;
    }

    public PolicyEntriesDefinition end() {
        return policyEntriesDefinition;
    }

    String getName() {
        return name;
    }
}
