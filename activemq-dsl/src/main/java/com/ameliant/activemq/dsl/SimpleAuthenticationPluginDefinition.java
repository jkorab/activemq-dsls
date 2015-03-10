package com.ameliant.activemq.dsl;

/**
 * @author jkorab
 */
public class SimpleAuthenticationPluginDefinition {

    private final PluginsDefinition pluginsDefinition;
    private UsersDefinition usersDefinition;

    SimpleAuthenticationPluginDefinition(PluginsDefinition pluginsDefinition) {
        assert (pluginsDefinition != null);
        this.pluginsDefinition = pluginsDefinition;
    }


    public UsersDefinition users() {
        usersDefinition = new UsersDefinition(this);
        return usersDefinition;
    }

    public PluginsDefinition end() {
        return pluginsDefinition;
    }

    UsersDefinition getUsersDefinition() {
        return usersDefinition;
    }
}
