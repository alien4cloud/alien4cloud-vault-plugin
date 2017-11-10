package org.alien4cloud.secret.vault.configuration;

import alien4cloud.ui.form.annotation.FormLabel;
import alien4cloud.ui.form.annotation.FormProperties;
import alien4cloud.ui.form.annotation.FormPropertyDefinition;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@FormProperties({ "user", "password" })
public class LDAPConfiguration {

    @FormPropertyDefinition(type = "string", description = "COMMON.USERNAME", isRequired = true)
    @FormLabel("COMMON.USERNAME")
    private String user;

    @FormPropertyDefinition(type = "string", description = "COMMON.PASSWORD", isPassword = true, isRequired = true)
    @FormLabel("COMMON.PASSWORD")
    private String password;
}
