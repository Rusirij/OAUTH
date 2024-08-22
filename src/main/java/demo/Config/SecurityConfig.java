package demo.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@Order(1)
// Extending WebSecurityConfigurerAdapter class allows customizing HTTP security for features, such as endpoints authorization or the authentication manager configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /*
     * We override another method from WebSecurityConfigurerAdapter to provide additional features on http security
     * Disable CSRF token
     * Enable all requests to be fully authenticated. We can add a filter here if we want to filter some specific urls from security
     * Used basic authentication here (username and password)
     *
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.requestMatchers()
          .antMatchers("/login", "/oauth/authorize")
          .and()
          .authorizeRequests()
          .anyRequest().authenticated()
          .and()
          .formLogin().permitAll();
    }

    /*
    Overriding configure method of WebSecurityConfigurerAdapter class to provide our own security implementation
    Here we chose in-memory auth for simplicity. We could have chosen to get the username and password from database
    */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
        	.inMemoryAuthentication()
            .withUser("rusiri")
            .password(passwordEncoder().encode("123456"))
            .roles("USER");
    }

    //Creating a password encoder bean which will be used to enccode the password before passing it over network to provide a layer of security.
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){ 
        return new BCryptPasswordEncoder(); 
    }
}