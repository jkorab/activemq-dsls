package com.ameliant.activemq.ports;

import com.ameliant.activemq.Experimental;

/**
 * @author jkorab
 */
@Experimental
public interface PortAllocator {

    int port();

}
