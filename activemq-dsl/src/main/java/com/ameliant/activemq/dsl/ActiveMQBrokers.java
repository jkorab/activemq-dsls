package com.ameliant.activemq.dsl;

import org.apache.commons.lang.Validate;

/**
 * @author jkorab
 */
public class ActiveMQBrokers {

    public static BrokerDefinition broker(String brokerName) {
        Validate.notEmpty(brokerName, "brokerName is empty");
        return new BrokerDefinition(brokerName);
    }
}
