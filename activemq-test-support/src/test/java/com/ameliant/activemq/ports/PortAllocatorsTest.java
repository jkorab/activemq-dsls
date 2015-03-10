package com.ameliant.activemq.ports;

import com.ameliant.activemq.Experimental;

import org.junit.Test;

import static org.junit.Assert.*;

@Experimental
public class PortAllocatorsTest {

    @Test
    public void testDslNextAvailable() {
        PortAllocator nextAvailable = PortAllocators.nextAvailable();
        int port1 = nextAvailable.port();
        assertTrue(port1 >= AvailablePortFinder.MIN_PORT_NUMBER);
        int port2 = nextAvailable.port();
        assertTrue(port2 >= AvailablePortFinder.MIN_PORT_NUMBER);
        assertNotEquals(port1, port2);
    }

    @Test
    public void testDslNextAvailableCached() {
        PortAllocator cached = PortAllocators.nextAvailable().cached();
        int port1 = cached.port();
        assertTrue(port1 >= AvailablePortFinder.MIN_PORT_NUMBER);
        int port2 = cached.port();
        assertTrue(port2 >= AvailablePortFinder.MIN_PORT_NUMBER);
        assertEquals(port1, port2);
    }

}