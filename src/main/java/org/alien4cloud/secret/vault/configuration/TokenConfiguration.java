package org.alien4cloud.secret.vault.configuration;

import alien4cloud.ui.form.annotation.FormPropertyDefinition;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenConfiguration {

    @FormPropertyDefinition(type = "string", isPassword = true, isRequired = true)
    private String token;
}
