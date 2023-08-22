package com.mx.proyecto.Dto;

import java.util.List;

public class ResponseDto {
	
	private Integer code;
	private String message;
	private Object content;
	private List<?> list;
	
	
	public ResponseDto(Integer code, String message, Object content) {
		super();
		this.code = code;
		this.message = message;
		this.content = content;
	}
	
	
	public ResponseDto() {

	}


	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getContent() {
		return content;
	}
	public void setContent(Object content) {
		this.content = content;
	}


	public List<?> getList() {
		return list;
	}


	public void setList(List<?> list) {
		this.list = list;
	}
	
}
