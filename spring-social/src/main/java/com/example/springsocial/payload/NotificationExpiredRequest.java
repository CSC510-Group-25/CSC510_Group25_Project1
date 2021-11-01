package com.example.springsocial.payload;

import javax.validation.constraints.NotBlank;

public class NotificationExpiredRequest {

    @NotBlank
    public String restaurant_name;

}
