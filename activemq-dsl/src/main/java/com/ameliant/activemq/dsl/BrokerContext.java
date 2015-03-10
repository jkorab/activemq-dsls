package com.ameliant.activemq.dsl;

import org.apache.activemq.broker.BrokerService;
import org.apache.commons.lang.Validate;

/**
 * @author jkorab
 */
public class BrokerContext {

    private final BrokerDefinition brokerDefinition;
    private BrokerService brokerService;

    public BrokerContext(BrokerDefinition brokerDefinition) {
        Validate.notNull(brokerDefinition, "brokerDefinition is null");
        this.brokerDefinition = brokerDefinition;
    }

    public void start() {
        brokerService = new BrokerServiceBuilder().build(brokerDefinition);
        try {
            brokerService.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void stop() {
        try {
            brokerService.stop();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
