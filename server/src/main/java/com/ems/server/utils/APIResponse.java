package com.ems.server.utils;

import com.ems.server.models.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class APIResponse {
    private Object data;
    private HttpStatus status;
    private String message;

    public APIResponse(HttpStatus status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
    }
