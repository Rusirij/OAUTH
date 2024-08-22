package demo.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer // marks the class as resource server. The API that provides access to the requested resource. It validates the access tokens and provides authorization.

/* @EnableResourceServer annotation means that your service (in terms of OAuth 2.0
- Resource Server) expects an access token in order to process the request.
 Access token should be obtained from Authorization Server by OAuth 2.0 Client before calling the Resource Server.
*/
public class OAuth2ResourceServer extends ResourceServerConfigurerAdapter 
{
	//Configure the resource details here.
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
        	.authorizeRequests()
        	.antMatchers("/").permitAll()
        	.antMatchers("/api/v1/**").authenticated(); // This tells the server to authenticate all the url's that starts with the mentioned pattern.
	}
}