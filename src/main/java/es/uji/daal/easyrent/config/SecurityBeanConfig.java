package es.uji.daal.easyrent.config;

import es.uji.daal.easyrent.utils.CustomAuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;

/**
 * Created by Alberto on 10/05/2016.
 */

@Configuration
public class SecurityBeanConfig {

    @Bean
    public AuthenticationProvider authenticationProvider() {
        return new CustomAuthenticationProvider();
    }
}
