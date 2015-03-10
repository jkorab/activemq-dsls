package com.ameliant.activemq.dsl;

/**
 * @author jkorab
 */
public class QueuePolicyEntryDefinition extends PolicyEntryDefinition {

    private int expireMessagesPeriod;

    QueuePolicyEntryDefinition(PolicyEntriesDefinition policyEntriesDefinition, String name) {
        super(policyEntriesDefinition, name);
    }

    public QueuePolicyEntryDefinition expireMessagesPeriod(int period) {
        this.expireMessagesPeriod = period;
        return this;
    }

    int getExpireMessagesPeriod() {
        return expireMessagesPeriod;
    }
}
