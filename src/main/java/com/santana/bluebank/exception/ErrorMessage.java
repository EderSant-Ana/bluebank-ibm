package com.santana.bluebank.exception;

import lombok.Data;

import java.util.Date;

@Data
public class ErrorMessage {

	private Integer status;
    private Date data;
    private String message;
    
	public ErrorMessage(Integer status, Date data, String message) {
		this.status = status;
		this.data = data;
		this.message = message;
	}

    
}
