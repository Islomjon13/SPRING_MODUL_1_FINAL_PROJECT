package com.example.spring_modul_1_final_project.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {
    private  String message;
    private  boolean status;
    private Object data;

    public Response(String message, boolean status) {
        this.message = message;
        this.status = status;
    }

}
