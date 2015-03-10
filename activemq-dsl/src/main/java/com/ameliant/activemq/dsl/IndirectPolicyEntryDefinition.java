package com.ameliant.activemq.dsl;

import org.apache.commons.lang.Validate;

/**
 * @author jkorab
 */
public class IndirectPolicyEntryDefinition {

    private final PolicyEntriesDefinition policyEntriesDefinition;
    private PolicyEntryDefinition policyEntryDefinition;

    IndirectPolicyEntryDefinition(PolicyEntriesDefinition policyEntriesDefinition) {
        assert(policyEntriesDefinition != null);
        this.policyEntriesDefinition = policyEntriesDefinition;
    }

    public QueuePolicyEntryDefinition queue(String name) {
        Validate.notEmpty(name, "queue name is empty");

        QueuePolicyEntryDefinition queuePolicyEntryDefinition = new QueuePolicyEntryDefinition(policyEntriesDefinition, name);
        policyEntryDefinition = queuePolicyEntryDefinition;
        return queuePolicyEntryDefinition;
    }

    public PolicyEntryDefinition getPolicyEntryDefinition() {
        return policyEntryDefinition;
    }
}
