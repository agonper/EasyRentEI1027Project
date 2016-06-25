package es.uji.daal.easyrent.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by Alberto on 09/05/2016.
 */

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/css/**", "/js/**", "/img/**",
                            "/", "/index*", "/about-us*", "/contact-us*", "/become-a-host*", "/signup*",
                            "/uploads/**/*", "/search*",
                            "/property/show/*", "/user/profile/*").permitAll()
                    .antMatchers("/user/list*", "/user/add*").permitAll()
                    .antMatchers("/admin/**").hasRole("ADMINISTRATOR")
                    .antMatchers("/login*").anonymous()
                    .anyRequest().authenticated()
                .and()
                .formLogin()
                    .loginPage("/login.html")
                    .defaultSuccessUrl("/index.html")
                    .and()
                .exceptionHandling()
                    .accessDeniedPage("/403.html");
    }
}
