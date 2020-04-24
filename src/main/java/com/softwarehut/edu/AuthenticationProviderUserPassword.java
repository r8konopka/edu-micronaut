package com.softwarehut.edu;

import com.softwarehut.edu.user.UsersStore;

import org.reactivestreams.Publisher;

import java.util.Collections;

import javax.inject.Singleton;

import io.micronaut.http.HttpRequest;
import io.micronaut.security.authentication.AuthenticationFailed;
import io.micronaut.security.authentication.AuthenticationRequest;
import io.micronaut.security.authentication.AuthenticationResponse;
import io.micronaut.security.authentication.HttpAuthenticationProvider;
import io.micronaut.security.authentication.UserDetails;
import io.reactivex.Flowable;

@Singleton
public class AuthenticationProviderUserPassword implements HttpAuthenticationProvider {

    UsersStore store;

    public AuthenticationProviderUserPassword(final UsersStore store) {
        this.store = store;
    }

    @Override
    public Publisher<AuthenticationResponse> authenticate(HttpRequest<?> request, AuthenticationRequest<?, ?> authenticationRequest) {
        final String username = authenticationRequest.getIdentity().toString();
        final String password = authenticationRequest.getSecret().toString();


        if (password.equals(store.getUserPassword(username))) {
            UserDetails details = new UserDetails(username, Collections.singletonList(store.getUserRole(username)));
            return Flowable.just(details);
        } else {
            return Flowable.just(new AuthenticationFailed());
        }
    }
}
