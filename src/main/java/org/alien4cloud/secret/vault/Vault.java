package org.alien4cloud.secret.vault;

import com.bettercloud.vault.SslConfig;
import org.alien4cloud.secret.ISecretProvider;
import org.alien4cloud.secret.exception.SecretProviderAuthenticationException;
import org.alien4cloud.secret.exception.NotSupportedAuthenticationMethod;
import org.alien4cloud.secret.vault.configuration.LDAPConfiguration;
import org.alien4cloud.secret.vault.configuration.TokenConfiguration;
import org.alien4cloud.secret.vault.configuration.VaultConfiguration;
import org.alien4cloud.secret.vault.constant.AuthenticationMethods;
import org.apache.commons.lang3.StringUtils;

import com.bettercloud.vault.VaultConfig;
import com.bettercloud.vault.response.AuthResponse;

import alien4cloud.model.secret.SecretAuthResponse;

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

    private String sanitizeURL(String url) {
        if (url.endsWith("/")) {
            return url.substring(0, url.length() - 1);
        } else {
            return url;
        }
    }

    @Override
    public SecretAuthResponse auth(VaultConfiguration vaultConfiguration, Object credentials) {
        TokenConfiguration tokenConfiguration = new TokenConfiguration();
        final com.bettercloud.vault.Vault vault;
        try {
            final VaultConfig vaultConfig = new VaultConfig().address(sanitizeURL(vaultConfiguration.getUrl()));
            if (StringUtils.isNotBlank(vaultConfiguration.getCertificate())) {
                vaultConfig.sslConfig(new SslConfig().pemUTF8(vaultConfiguration.getCertificate()).build());
            }
            switch (vaultConfiguration.getAuthenticationMethod()) {
            case AuthenticationMethods.LDAP:
                // login with ldap
                vault = new com.bettercloud.vault.Vault(vaultConfig.build());
                LDAPConfiguration ldapCredentials = (LDAPConfiguration) credentials;
                AuthResponse authResponse = vault.auth().loginByLDAP(ldapCredentials.getUser(), ldapCredentials.getPassword());
                tokenConfiguration.setToken(authResponse.getAuthClientToken());
                break;
            case AuthenticationMethods.TOKEN:
                // auth the given token
                TokenConfiguration tokenCredentials = (TokenConfiguration) credentials;
                vault = new com.bettercloud.vault.Vault(vaultConfig.token(tokenCredentials.getToken()).build());
                vault.auth().lookupSelf();
                tokenConfiguration.setToken(tokenCredentials.getToken());
                break;
            default:
                throw new NotSupportedAuthenticationMethod("Authentication method [" + vaultConfiguration.getAuthenticationMethod() + "] is not supported");
            }
        } catch (Exception e) {
            throw new SecretProviderAuthenticationException("Authentication fails", e);
        }
        VaultConfiguration newVaultConfiguration = new VaultConfiguration();
        newVaultConfiguration.setAuthenticationMethod(AuthenticationMethods.TOKEN);
        newVaultConfiguration.setUrl(vaultConfiguration.getUrl());
        newVaultConfiguration.setCertificate(vaultConfiguration.getCertificate());
        SecretAuthResponse result = new SecretAuthResponse();
        result.setConfiguration(newVaultConfiguration);
        result.setCredentials(tokenConfiguration);
        return result;
    }

}
