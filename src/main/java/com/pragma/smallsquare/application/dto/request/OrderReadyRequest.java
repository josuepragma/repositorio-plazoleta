package com.pragma.smallsquare.application.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class OrderReadyRequest {

    @NotNull(message = "STATUS field is required")
    private String status;

    @NotNull(message = "SECURITY PIN field is required")
    private String securityPin;
}
