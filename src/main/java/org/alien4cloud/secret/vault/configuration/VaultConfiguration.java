package org.alien4cloud.secret.vault.configuration;

import javax.validation.constraints.NotNull;

import alien4cloud.ui.form.annotation.FormPropertyConstraint;
import alien4cloud.ui.form.annotation.FormValidValues;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VaultConfiguration {

    @FormPropertyConstraint(pattern = "http\\:.+(?:\\d+)")
    @NotNull
    private String url;

    @FormValidValues({ "token", "ldap" })
    @NotNull
    private String authenticationMethod;
}
