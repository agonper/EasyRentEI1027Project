package es.uji.daal.easyrent.config;

import es.uji.daal.easyrent.utils.BCryptPasswordEncryptor;
import es.uji.daal.easyrent.utils.PasswordEncryptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Alberto on 08/05/2016.
 */

@Configuration
public class CustomBeanConfig {

    @Bean
    public PasswordEncryptor passwordEncryptor() {
        return new BCryptPasswordEncryptor();
    }
}
