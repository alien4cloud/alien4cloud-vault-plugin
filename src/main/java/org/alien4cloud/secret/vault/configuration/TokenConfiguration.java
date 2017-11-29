package org.alien4cloud.secret.vault.configuration;

import javax.validation.constraints.NotNull;

import alien4cloud.ui.form.annotation.FormLabel;
import alien4cloud.ui.form.annotation.FormPassword;
import alien4cloud.ui.form.annotation.FormProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@FormProperties({ "token" })
public class TokenConfiguration {

    @NotNull
    @FormPassword
    @FormLabel("ORCHESTRATORS.LOCATIONS.SECRETS.TOKEN")
    private String token;
}
