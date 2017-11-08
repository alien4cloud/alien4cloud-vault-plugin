package org.alien4cloud.secret.vault;

import org.alien4cloud.secret.ISecretProvider;
import org.alien4cloud.secret.exception.NotSupportedAuthenticationMethod;
import org.alien4cloud.secret.vault.configuration.LDAPConfiguration;
import org.alien4cloud.secret.vault.configuration.TokenConfiguration;
import org.alien4cloud.secret.vault.configuration.VaultConfiguration;
import org.alien4cloud.secret.vault.constant.AuthenticationMethods;

public class Vault implements ISecretProvider<VaultConfiguration> {

    @Override
    public Class<VaultConfiguration> getConfigurationDescriptor() {
        return VaultConfiguration.class;
    }

    @Override
    public Class<?> getAuthenticationConfigurationDescriptor(VaultConfiguration configuration) {
        switch (configuration.getAuthenticationMethod()) {
        case AuthenticationMethods.LDAP:
            return LDAPConfiguration.class;
        case AuthenticationMethods.TOKEN:
            return TokenConfiguration.class;
        default:
            throw new NotSupportedAuthenticationMethod("Authentication method [" + configuration.getAuthenticationMethod() + "] is not supported");
        }
    }
}
