package org.alien4cloud.secret.vault.configuration;

import javax.validation.constraints.NotNull;

import alien4cloud.ui.form.annotation.FormLabel;
import alien4cloud.ui.form.annotation.FormPassword;
import alien4cloud.ui.form.annotation.FormProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@FormProperties({ "user", "password" })
public class LDAPConfiguration {

    @FormLabel("COMMON.USERNAME")
    @NotNull
    private String user;

    @FormLabel("COMMON.PASSWORD")
    @FormPassword
    @NotNull
    private String password;
}
