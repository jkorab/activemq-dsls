package com.ameliant.activemq.dsl;

import org.apache.commons.lang.Validate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jkorab
 */
public class UsersDefinition {

    private final SimpleAuthenticationPluginDefinition simpleAuthenticationPluginDefinition;
    private final List<AuthenticationUserDefinition> authenticationUserDefinitions = new ArrayList<>();

    UsersDefinition(SimpleAuthenticationPluginDefinition simpleAuthenticationPluginDefinition) {
        assert (simpleAuthenticationPluginDefinition != null);
        this.simpleAuthenticationPluginDefinition = simpleAuthenticationPluginDefinition;
    }

    public AuthenticationUserDefinition authenticationUser(String username) {
        Validate.notEmpty(username, "username is empty");

        AuthenticationUserDefinition authenticationUserDefinition = new AuthenticationUserDefinition(this, username);
        authenticationUserDefinitions.add(authenticationUserDefinition);
        return authenticationUserDefinition;
    }

    public SimpleAuthenticationPluginDefinition end() {
        Validate.notEmpty(authenticationUserDefinitions, "No authenticationUsers defined");
        return simpleAuthenticationPluginDefinition;
    }

    List<AuthenticationUserDefinition> getAuthenticationUserDefinitions() {
        return authenticationUserDefinitions;
    }
}
