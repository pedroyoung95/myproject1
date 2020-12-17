package register.service;

import java.util.Map;

public class JoinRequest {
	
	private String id;
	private String name;
	private String password;
	private String confirmPassword;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	public void validate(Map<String, Boolean> errors) {
		checkEmpty(errors, id, "id");
		
		checkEmpty(errors, name, "name");
		
		checkEmpty(errors, password, "password");
		
		checkEmpty(errors, confirmPassword, "confirmPassword");
		
		
		if(!errors.containsKey("confirmPassword")) {
			if(isPasswordEqualsConfirm()) {
				errors.put("notMatch", true);
			}
		}		
	}
	
	public boolean isPasswordEqualsConfirm() {
		return password != null || password.equals(confirmPassword);
	}
	
	public void checkEmpty(Map<String, Boolean> errors, String value, String fieldName) {
		if(value == null || value.isEmpty()) {
			errors.put(fieldName, true);
		}
	}
}
