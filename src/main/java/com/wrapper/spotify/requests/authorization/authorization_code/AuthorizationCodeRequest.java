package com.wrapper.spotify.requests.authorization.authorization_code;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import com.wrapper.spotify.requests.authorization.AbstractAthorizationRequest;

import java.io.IOException;
import java.net.URI;

public class AuthorizationCodeRequest extends AbstractAthorizationRequest {

  private AuthorizationCodeRequest(Builder builder) {
    super(builder);
  }

  @SuppressWarnings("unchecked")
  public AuthorizationCodeCredentials execute() throws
          IOException,
          SpotifyWebApiException {
    return new AuthorizationCodeCredentials.JsonUtil().createModelObject(postJson());
  }

  public static final class Builder extends AbstractAthorizationRequest.Builder<Builder> {

    public Builder(final String clientId, final String clientSecret) {
      super(clientId, clientSecret);
    }

    public Builder grant_type(final String grant_type) {
      assert (grant_type != null);
      assert (grant_type.equals("authorization_code"));
      return setFormParameter("grant_type", grant_type);
    }

    public Builder code(final String code) {
      assert (code != null);
      assert (!code.equals(""));
      return setFormParameter("code", code);
    }

    public Builder redirect_uri(final URI redirect_uri) {
      assert (redirect_uri != null);
      return setFormParameter("redirect_uri", redirect_uri.toString());
    }

    public AuthorizationCodeRequest build() {
      setHeader("Content-Type", "application/x-www-form-urlencoded");
      setHost(SpotifyApi.DEFAULT_AUTHENTICATION_HOST);
      setPort(SpotifyApi.DEFAULT_AUTHENTICATION_PORT);
      setScheme(SpotifyApi.DEFAULT_AUTHENTICATION_SCHEME);
      setPath("/api/token");

      return new AuthorizationCodeRequest(this);
    }
  }
}
