package com.fourninja.goblin.common.utils;

import java.io.Serializable;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class TimeProvider implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6724457094665819308L;
	
	public Date now() {
        return new Date();
    }

}
