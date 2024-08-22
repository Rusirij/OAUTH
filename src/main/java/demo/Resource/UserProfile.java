package demo.Resource;

// A object to represent users. Every user profile has a name and email
public class UserProfile {
	private String name;
	private String email;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	// The toString method is used to return the string representation of the object.
	@Override
	public String toString() {
		return "UserProfile [name=" + name + ", email=" + email + "]";
	}
}