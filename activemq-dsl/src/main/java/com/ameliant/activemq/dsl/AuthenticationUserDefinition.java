package com.ameliant.activemq.dsl;

import org.apache.commons.lang.Validate;

/**
 * @author jkorab
 */
public class AuthenticationUserDefinition {

    private final UsersDefinition usersDefinition;
    private final String username;
    private String password;
    private String groups;

    AuthenticationUserDefinition(UsersDefinition usersDefinition, String username) {
        assert (usersDefinition != null);
        assert (username != null);
        this.usersDefinition = usersDefinition;
        this.username = username;
    }

    public AuthenticationUserDefinition password(String password) {
        Validate.notEmpty(password, "password is empty");
        this.password = password;
        return this;
    }

    public AuthenticationUserDefinition groups(String groups) {
        Validate.notEmpty(groups, "groups is empty");
        this.groups = groups;
        return this;
    }

    public UsersDefinition end() {
        Validate.notNull(password, "password is null");
        Validate.notNull(groups, "groups is null");
        return usersDefinition;
    }

    String getGroups() {
        return groups;
    }

    String getPassword() {
        return password;
    }

    String getUsername() {
        return username;
    }
}
