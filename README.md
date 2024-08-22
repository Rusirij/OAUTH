# OAUTH
OAuth 2.0 stands for “Open Authorization”, is a standard designed to allow a website or application to access resources hosted by other web apps on behalf of a user
Common scenarios where Oauth is used are Login via Google, Facebook where one website allows a user to log in using authorization codes generated.

## OAUTH Roles
OAuth specifies four roles:
- Resource owner (the User) – an entity capable of granting access to a protected resource (for example end-user).
- Resource server (the API server) – the server hosting the protected resources, capable of accepting and responding to protected resource requests using access tokens.
- Client – an application making protected resource requests on behalf of the resource owner and with its authorization.
- Authorization server – The server issuing access tokens to the client after successfully authenticating the resource owner and obtaining authorization.

### OAuth 2 provides several "grant types" for different use cases. 
- Authorization Code
- Password
- Client credentials
- Implicit

**Password grant type is a way to exchange a user's credentials for an access token. The client application will collect the user's password and send it to the authorization server**


![image](https://github.com/user-attachments/assets/c48b55fd-fefe-4f7a-8473-660fb8ea3387)


### OAUTH Flow
![image](https://github.com/user-attachments/assets/40d176e3-ad15-458d-a4c9-7e513821fffc)


## Implementation
Download a spring boot application from Spring Initializr similar to the basic authentication app.
Add oauth dependency in pom.xml
![image](https://github.com/user-attachments/assets/18fcd0b8-c89f-4a04-9c04-01eb5b959f19)


We will be adding Security Configuration class which will provide HttpSecurity and Web Security configurations similar to what we added for Basic Authentication
Extend the class with WebSecurityConfigurerAdapter and override the method for Http Security

- Create a class named OAuth2ResourceServer extending ResourceServerConfigurerAdapter
- Annotate class with @EnableResourceServer which enables the functionality for Resource server
- Override the method in the ResourceServerConfigurerAdapter class adding a pattern that has to be authenticated.
- The configure(HttpSecurity http) method configures the access rules and request matchers (path) for protected resources using the HttpSecurity class. 

Create a class named OAuth2AuthorizationServer extending AuthorizationServerConfigurerAdapter
Annotate class with @EnableAuthorizationServer which enables the functionality for Authorization server
Enable endpoints for checking by overriding the configure (AuthorizationServerSecurityConfigureroauthServer) method.
Override another method for providing information about ClientDetails
Provide details such as clientID, authorization grant types, resource ids,
Redirect url , token expiration details etc.


Once you type the url on browser, you will be prompted with a login page
Enter the credentials configured in SecurityConfig class and sign in.
After sign in , you will be prompted with a screen for Oauth approval

![image](https://github.com/user-attachments/assets/4a059155-b63d-4a62-ba48-b75bcb66ed12)








