package com.plebicom.site.factory;

import org.springframework.stereotype.Service;

import com.plebicom.site.dto.ResponseDTO;
import com.plebicom.site.dto.ResponseDTO.RESPONSE_TYPE;

@Service
public class ResponseFactory {

	public ResponseDTO createSuccessResponse(Object object) {
		
		ResponseDTO dto = new ResponseDTO();
		dto.setType(RESPONSE_TYPE.SUCCESS);
		dto.setData(object);
		return dto;
	}
	
	public ResponseDTO createErrorResponse(String msg) {
		
		ResponseDTO dto = new ResponseDTO();
		dto.setType(RESPONSE_TYPE.ERROR);
		dto.setMessage(msg);
		dto.setData(null);
		return dto;
	}
}
