package com.santana.bluebank.exception;

import lombok.Data;

import java.util.Date;

@Data
public class ErrorMessage {

	private Integer status;
    private Date date;
    private String message;
    
	public ErrorMessage(Integer status, Date date, String message) {
		this.status = status;
		this.date = date;
		this.message = message;
	}

    
}
