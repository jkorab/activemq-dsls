package com.ameliant.activemq.dsl;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jkorab
 */
public class PolicyEntriesDefinition {

    private final PolicyMapDefinition policyMapDefinition;
    private final List<IndirectPolicyEntryDefinition> indirectPolicyEntryDefinitions = new ArrayList<>();

    public PolicyEntriesDefinition(PolicyMapDefinition policyMapDefinition) {
        assert (policyMapDefinition != null);
        this.policyMapDefinition = policyMapDefinition;
    }

    public IndirectPolicyEntryDefinition policyEntry() {
        IndirectPolicyEntryDefinition indirectPolicyEntryDefinition = new IndirectPolicyEntryDefinition(this);
        indirectPolicyEntryDefinitions.add(indirectPolicyEntryDefinition);
        return indirectPolicyEntryDefinition;
    }

    public PolicyMapDefinition end() {
        return policyMapDefinition;
    }

    List<PolicyEntryDefinition> getPolicyEntryDefinitions() {
        List<PolicyEntryDefinition> policyEntryDefinitions = new ArrayList<>();
        for (IndirectPolicyEntryDefinition indirect : indirectPolicyEntryDefinitions) {
            policyEntryDefinitions.add(indirect.getPolicyEntryDefinition());
        }
        return policyEntryDefinitions;
    }
}
