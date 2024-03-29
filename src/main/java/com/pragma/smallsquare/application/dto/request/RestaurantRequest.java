package com.pragma.smallsquare.application.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
public class RestaurantRequest {

    @NotEmpty(message = "RESTAURANT NAME field is required")
    @Pattern(regexp = "^.*[a-zA-Z]+.*$", message = "RESTAURANT NAME must have one letter at least")
//    @Pattern(regexp = "^\\d*[a-zA-Z][a-zA-Z0-9]*$", message = "RESTAURANT NAME must have one letter at least")
    String name;

    @NotEmpty(message = "RESTAURANT NIT field is required")
    @Pattern(regexp = "^\\d*$", message = "NIT must be only numbers")
    String nit;

    @NotEmpty(message = "RESTAURANT ADDRESS field is required")
    String address;

    @NotEmpty(message = "RESTAURANT PHONE field is required")
    @Pattern(regexp = "^\\+?\\d*$", message = "Invalid PHONE format")
    @Size(max = 13, message = "PHONE must have 13 digits maximum")
    String phone;

    @NotEmpty(message = "RESTAURANT URL LOGO field is required")
    String urlLogo;

    @NotNull(message = "RESTAURANT OWNER field is required")
    Integer idOwner;
}
