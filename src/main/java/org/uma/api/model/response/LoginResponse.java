package org.uma.api.model.response;

public class LoginResponse {
	
	public LoginResponse(String token, String userId, String message) {
		super();
		this.token = token;
		this.userId = userId;
		this.message = message;
	}

	public LoginResponse()
	{}



	private String token;
	private String userId;
	private String message;
	
	
	public String getToken() {
		return token;
	}



	public void setToken(String token) {
		this.token = token;
	}



	public String getUserId() {
		return userId;
	}



	public void setUserId(String userId) {
		this.userId = userId;
	}



	public String getMessage() {
		return message;
	}



	public void setMessage(String message) {
		this.message = message;
	}




	
	@Override
	public String toString() {
		return "LoginResponse [token=" + token + ", userId=" + userId + ", message=" + message + "]";
	}

}
