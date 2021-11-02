package com.santana.bluebank.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

@Getter
@Setter
public class ErrorMessage {

    private Date data;
    private String message;

    public ErrorMessage(Date data, String message) {
        this.data = data;
        this.message = message;
    }
}
