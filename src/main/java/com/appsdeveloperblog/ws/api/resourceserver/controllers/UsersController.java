package com.appsdeveloperblog.ws.api.resourceserver.controllers;

import com.appsdeveloperblog.ws.api.resourceserver.response.UserRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import static com.appsdeveloperblog.ws.api.resourceserver.controllers.ControllerConstants.OR;
import static com.appsdeveloperblog.ws.api.resourceserver.controllers.ControllerConstants.ROLE_DEVELOPER;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    Environment env;

    @GetMapping("/status/check")
    public String status() {
        return "Working on port: " + env.getProperty("local.server.port");
    }

    @PreAuthorize(ROLE_DEVELOPER + OR +  "#id == #jwt.subject")
    //@Secured("ROLE_developer")
    @DeleteMapping(path = "/{id}")
    public String deleteUser(@PathVariable String id, @AuthenticationPrincipal Jwt jwt) {
        return "Deleted user with id " + id + " and JWT subject " + jwt.getSubject();
    }


    @PostAuthorize("returnObject.userId == #jwt.subject")
    @GetMapping(path = "/{id}")
    public UserRest getUser(@PathVariable String id, @AuthenticationPrincipal Jwt jwt) {
        return new UserRest("Ibrahim", "Mohsen","30542d5a-7ad5-4d08-8142-f699828830c9");
    }
}
