package com.appsdeveloperblog.ws.api.resourceserver.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRest {
    private String userFirstName;
    private String userLastName;
    private String userId;
}
