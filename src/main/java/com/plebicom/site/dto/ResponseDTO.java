package com.plebicom.site.dto;

import lombok.Data;

@Data
public class ResponseDTO {

	RESPONSE_TYPE type;
	
	String message;
	
	Object data;
	
	public enum RESPONSE_TYPE {
		ERROR,
		SUCCESS,
		UNAUTHORIZED
	}
}
