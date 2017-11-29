package org.alien4cloud.secret.vault.configuration;

import alien4cloud.ui.form.annotation.FormLabel;
import org.alien4cloud.tosca.normative.types.ToscaTypes;

import alien4cloud.ui.form.annotation.FormProperties;
import alien4cloud.ui.form.annotation.FormPropertyConstraint;
import alien4cloud.ui.form.annotation.FormPropertyDefinition;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@FormProperties({ "url", "authenticationMethod", "certificate" })
public class VaultConfiguration {

    @FormPropertyDefinition(type = ToscaTypes.STRING, description = "ORCHESTRATORS.LOCATIONS.SECRETS.VAULT_URL", isRequired = true)
    @FormLabel("ORCHESTRATORS.LOCATIONS.SECRETS.VAULT_URL")
    private String url;

    @FormPropertyDefinition(type = ToscaTypes.STRING, description = "ORCHESTRATORS.LOCATIONS.SECRETS.VAULT_CERTIFICATE")
    @FormLabel("ORCHESTRATORS.LOCATIONS.SECRETS.VAULT_CERTIFICATE")
    private String certificate;

    @FormPropertyDefinition(type = ToscaTypes.STRING, description = "ORCHESTRATORS.LOCATIONS.SECRETS.AUTHENTICATION_METHOD", isRequired = true, constraints = @FormPropertyConstraint(validValues = {
            "token", "ldap" }))
    @FormLabel("ORCHESTRATORS.LOCATIONS.SECRETS.AUTHENTICATION_METHOD")
    private String authenticationMethod;
}
