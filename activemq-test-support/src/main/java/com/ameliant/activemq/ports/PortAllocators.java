package com.ameliant.activemq.ports;

import com.ameliant.activemq.Experimental;

/**
 * @author jkorab
 */
@Experimental
public class PortAllocators {

    public static CacheablePortAllocator nextAvailable() {

        return new CacheablePortAllocator() {

            private PortAllocator cached;
            @Override
            public PortAllocator cached() {
                if (cached == null) {
                    final PortAllocator outer = this;
                    cached = new PortAllocator() {
                        private Integer cachedPort;
                        @Override
                        public int port() {
                            if (cachedPort == null) {
                                cachedPort = outer.port();
                            }
                            return cachedPort;
                        }
                    };
                }
                return cached;
            }

            @Override
            public int port() {
                return AvailablePortFinder.getNextAvailable();
            }
        };
    }

}
