package com.example.backend.util;

import com.example.backend.models.view.UserViewModel;
import org.springframework.core.env.Environment;
import org.springframework.web.util.UriComponentsBuilder;

public class TripInvitationEmailContext extends AbstractEmailContext{

    private final Environment env;

    public TripInvitationEmailContext(Environment env) {
        super();
        this.env = env;
    }

    @Override
    public <T> void init(T context) {

        UserViewModel passenger = (UserViewModel) context;

        setSubject("Запиши се за пътуване");
        setTemplateLocation("emailForRegistration");
        setFrom(env.getProperty("EMAIL_USERNAME"));
        setTo(passenger.getUsername());

        super.init(context);
    }

    public String buildInvitationUrl(final String baseUrl, final String id, final String tripId) {
        final String url = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .path("/portal/trips/confirm-invitation").queryParam("id", id).queryParam("tripId", tripId)
                .toUriString();

        return url;
    }
}
