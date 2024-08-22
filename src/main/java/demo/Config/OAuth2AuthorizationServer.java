package demo.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@Configuration // Configuration class
@EnableAuthorizationServer
/*
* This is the server that issues access token to the clients. It is the main engine of OAuth.
* Extending AuthorizationServerConfigurerAdapter is just used for customization of the Authorization Server.
* You can easily set up a functioning Authorization Server
* within Spring Security by Just Annotating a Bean class with @EnableAuthorizationServer.
*/
public class OAuth2AuthorizationServer extends AuthorizationServerConfigurerAdapter
// This can be used to configure security of your authorization server itself
// i.e. which user can generate tokens , changing default realm etc.
{
    //Implementation of PasswordEncoder that uses the BCrypt strong hashing function
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
 
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        // Here you will do non-security configs for end points associated with your Authorization Server
        // and can specify details about authentication manager, token generation etc.
        security
            .tokenKeyAccess("permitAll()")
            .checkTokenAccess("isAuthenticated()")
            .allowFormAuthenticationForClients();
    }
 
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // Here you will specify about `ClientDetailsService`
        // i.e. information about OAuth2 clients & where their info is located - memory , DB , LDAP etc.
        // and other configs listed below
        clients
            .inMemory()
                // Client details. we will provide client name in the url (see line 55)
            .withClient("clientapp").secret(passwordEncoder.encode("123456"))
            .authorizedGrantTypes("password", "authorization_code", "refresh_token") // internal to oauth2 operation
            .authorities("READ_ONLY_CLIENT") // Authorities provided to client
            .scopes("read_profile_info") // defining the scope which will be part of the url ( see url on line 55)
            .resourceIds("oauth2-resource") // A resource id to create the oauth2 resource
            .redirectUris("http://localhost:9090/login") // redirect to this url for login when user request any page
            .accessTokenValiditySeconds(120) // Validity of access token in seconds
            .refreshTokenValiditySeconds(240000); // Validity of refresh token in seconds. This refresh token is used to get a new access
                                                    // token when it is expired.
    }
  //  http://localhost:8080/oauth/authorize?client_id=clientapp&response_type=code&scope=read_profile_info

//http://localhost:9090/oauth/authorize?response_type=code&client_id=clientapp&redirect_uri=http://localhost:9090/login&scope=read_profile_info

}