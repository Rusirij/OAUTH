package demo.Resource;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//create RESTful web services using Spring MVC.
// This annotation is used at the class level and allows the class to handle the requests made by the client.
@Controller
public class RestResource 
{
	//It is used to map web requests onto specific handler classes and/or handler methods. Here our mapping will be localhost:{port}/api/users/me
	@RequestMapping("/api/users/me")
	public ResponseEntity<UserProfile> profile() 
	{
		//Build some dummy data to return for testing
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String email = user.getUsername() + "@edureka.com";

		UserProfile profile = new UserProfile();
		profile.setName(user.getUsername());
		profile.setEmail(email);

		//Returning the user profile we created
		return ResponseEntity.ok(profile);
	}
}