ActiveMQ DSLs
=============

This project defines a pair of Java DSLs for working with ActiveMQ. It was originally created to allow a simplified
mechanism for defining broker configurations inside unit tests without resorting to poking around in ActiveMQ's
internal classes, or creating lots of similar-but-different broker configs using ActiveMQ XML configuration files.

Note: these DSLs are at this stage in pre-alpha. The only tests that validate them are part of another project.

activemq-dsl
------------
This project defines a DSL that mimics the concepts of Apache Camel's Java DSL.
The idea is that it allows for brokers to be defined in-line, using a syntax that mirrors ActiveMQ's XML configuration.

To define a broker, you instantiate a `BrokerContext` with a `BrokerDefinition`, then call the `start()` method.
At some stage in the future when you want to shut down, you call the `stop()` method.

A `BrokerDefinition` is defined via a fluent builder DSL created via the `ActiveMQBrokers.broker()` method.

Here is an example of the DSL usage:

    BrokerContext context = new BrokerContext(
        ActiveMQBrokers.broker("embeddedBroker").useJmx(false).persistent(false)
            .transportConnectors()
                .transportConnector("openwire", "tcp://0.0.0.0:61616").end()
            .end());

    context.start();
    // later ...
    context.end();

The configuration syntax should be familiar to anyone who has dealt with ActiveMQ in the past.

While this DSL was originally designed for testing, there is no reason why it cannot be used to define a broker.

At present, the DSL supports the partial configuration of the following elements only (not all properties are configurable):
* `transportConnectors`
* `networkConnectors`
* `managementContext`
* `plugins` - simple authentication only
* `policyEntry`

activemq-test-support
---------------------
This project provides a complementary DSL for JUnit testing with brokers defined using the ActiveMQ DSL.
It uses JUnit's `@Rule/ExternalResource` functionality to control embedded broker startup and shutdowns without the need
to deal with this aspect of the broker lifecycle in `@Before/setUp()` and `@After/tearDown()` methods.

To use an embedded broker, you use the DSL as follows:

    public class MyEmbeddedBrokerTest {
        @Rule
        public BrokerResource broker = new BrokerResource(
                ActiveMQBrokers.broker("embeddedBroker").useJmx(false).persistent(false)
                    .transportConnectors()
                        .transportConnector("openwire", "tcp://0.0.0.0:61616").end()
                    .end());

        // you could define whole broker networks by instantiating multiple BrokerResources and wiring up their
        // networkConnectors

        @Test
        public void testEmbeddedBroker() {
            ConnectionFactory cf =
            new ActiveMQConnectionFactory(broker.getTcpConnectionUri("openwire")); // named lookup
            // ...
        }
    }

The DSL also supports proxying using the `SocketProxy` class found inside ActiveMQ's own tests. This can be used
to easily simulate network outages between the ActiveMQ client and broker.

To use proxying, you make use of a `ProxiedBrokerResource` as follows:

    public class MyEmbeddedBrokerTest {
        @Rule
        public ProxiedBrokerResource broker = new BrokerResource(
                ActiveMQBrokers.broker("embeddedBroker").useJmx(false).persistent(false)
                    .transportConnectors()
                        .transportConnector("openwire", "tcp://0.0.0.0:61616").end()
                        .transportConnector("stomp", "stomp://0.0.0.0:61618").end()
                    .end())
                .withProxied("openwire").port(10000); // port to be proxied is looked up by name
                // you can define multiple proxies for the one broker!

        @Test
        public void testNetworkOutages() {
            ConnectionFactory cf =
                new ActiveMQConnectionFactory(
                    "failover:(" + broker.getTcpConnectionUri("openwire") + ")"); // returns proxied port 10000
            // ...
            SocketProxy socketProxy = broker.getProxy("openwire");
            socketProxy.close(); // network goes down
            // ...
            socketProxy.reopen(); // network comes back up
        }
    }

Simple!
