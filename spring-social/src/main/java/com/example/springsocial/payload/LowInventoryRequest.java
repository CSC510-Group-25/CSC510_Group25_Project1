package com.example.springsocial.payload;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class LowInventoryRequest {

    @NotBlank
    public String restaurant_name;

    @NotNull
    @Min(1)
    public int max_qty;
}
