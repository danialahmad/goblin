package com.fourninja.goblin.form.response.authentication;

import java.io.Serializable;

public class AuthenticationResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3561343125544830451L;
	
	private final String token;

    public AuthenticationResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }

}
