package com.appsdeveloperblog.ws.api.resourceserver.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        /*JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new KeycloakRoleConverter());*/

        http//.cors().and()
                .authorizeRequests()
                .mvcMatchers(HttpMethod.GET, "/users/status/check")
                //.hasAuthority("SCOPE_profile")
                .hasRole("developer")
                //.hasAnyAuthority("ROLE_developer") //if we use hasAnyAuthority to add role name we must attach the prefix ROLE_
                //.hasAnyRole("devleoper","user") //to support multiple role
                .anyRequest().authenticated()
                .and()
                .oauth2ResourceServer()
                .jwt();
                /*.jwt()
                .jwtAuthenticationConverter(jwtAuthenticationConverter);*/
    }
}
