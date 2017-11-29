package org.alien4cloud.secret.vault;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Scope;

@Configuration
@ImportResource("classpath:alien-properties-config.xml")
public class PluginConfiguration {

    @Bean(name = "vault")
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    public Vault vault() {
        return new Vault();
    }
}
