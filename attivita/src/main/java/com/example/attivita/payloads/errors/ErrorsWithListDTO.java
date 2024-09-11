package com.example.attivita.payloads.errors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorsWithListDTO {
    private String message;
    private Date date;
    private List<String> messageList;
}
