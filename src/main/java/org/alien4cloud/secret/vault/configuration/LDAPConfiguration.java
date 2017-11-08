package org.alien4cloud.secret.vault.configuration;

import javax.validation.constraints.NotNull;

import alien4cloud.ui.form.annotation.FormPropertyDefinition;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LDAPConfiguration {

    @NotNull
    private String user;

    @FormPropertyDefinition(type = "string", isPassword = true, isRequired = true)
    private String password;
}
