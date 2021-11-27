package com.example.backend.util;

import com.example.backend.models.entity.User;
import org.springframework.core.env.Environment;
import org.springframework.web.util.UriComponentsBuilder;

public class AccountVerificationEmailContext extends AbstractEmailContext{

    private final Environment env;

    public AccountVerificationEmailContext(Environment env) {
        super();
        this.env = env;
    }

    @Override
    public <T> void init(T context) {

        User customer = (User) context;

        setSubject("Завършете своята регистрация");
        setTemplateLocation("emailForRegistration");
        setFrom(env.getProperty("EMAIL_USERNAME"));
        setTo(customer.getUsername());
    }

    public void setToken(String token) {
        put("token", token);
    }

    public String buildVerificationUrl(final String baseUrl, final String token) {
        final String url = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .path("/portal/users/register/verify").queryParam("token", token)
                .toUriString();

        return url;
    }

}


