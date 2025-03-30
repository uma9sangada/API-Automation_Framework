package org.uma.api.model.request;

public class LoginRequest {
	
	
	    @Override
	public String toString() {
		return "LoginRequest [userEmail=" + userEmail + ", userPassword=" + userPassword + "]";
	}
		public String userEmail;
	    public String userPassword;
	    
	    public LoginRequest() {}
	    
	    public LoginRequest(String userEmail, String userPassword) {
			super();
			this.userEmail = userEmail;
			this.userPassword = userPassword;
		}
		
	    
	    
	    public String getUserEmail() {
			return userEmail;
		}
		public void setUserEmail(String userEmail) {
			this.userEmail = userEmail;
		}
		public String getUserPassword() {
			return userPassword;
		}
		public void setUserPassword(String userPassword) {
			this.userPassword = userPassword;
		}
		
	
}
