package org.alien4cloud.secret.vault.configuration;

import alien4cloud.ui.form.annotation.FormLabel;
import alien4cloud.ui.form.annotation.FormProperties;
import alien4cloud.ui.form.annotation.FormPropertyDefinition;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@FormProperties({ "token" })
public class TokenConfiguration {

    @FormPropertyDefinition(type = "string", description = "ORCHESTRATORS.LOCATIONS.SECRETS.TOKEN", isPassword = true, isRequired = true)
    @FormLabel("ORCHESTRATORS.LOCATIONS.SECRETS.TOKEN")
    private String token;
}
