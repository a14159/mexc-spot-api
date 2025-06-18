package io.contek.invoker.mexc.spot.api.rest;

import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.actor.http.AnyHttpException;
import io.contek.invoker.commons.rest.*;
import io.contek.invoker.security.ICredential;
import is.fm.util.Escaper;
import is.fm.util.Escapers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.concurrent.NotThreadSafe;
import java.time.Clock;
import java.util.Map;

import static io.contek.invoker.commons.rest.RestMediaType.JSON;

@NotThreadSafe
public abstract class RestRequest<R> extends BaseRestRequest<R> {

  private static final Escaper urlPathSegmentEscaper = Escapers.urlPathSegmentEscaper();
  private static final Logger log = LogManager.getLogger(RestRequest.class);
  private final RestContext context;
  private final Clock clock;

  protected RestRequest(IActor actor, RestContext context) {
    super(actor);
    this.context = context;
    clock = actor.getClock();
  }

  protected abstract RestMethod getMethod();

  protected abstract String getEndpointPath();

  protected abstract RestParams getParams();

  @Override
  protected final RestCall createCall(ICredential credential) {
    RestMethod method = getMethod();
    switch (method) {
      case GET, DELETE -> {
          String paramsString = buildParamsString();
          if (!credential.isAnonymous()) {
            StringBuilder sb = new StringBuilder(128);
            sb.append(paramsString);
            String toSign = paramsString;
            // if no timestamp param append it
            if (paramsString.lastIndexOf("timestamp") < 0) {
              String timestamp = Long.toString(clock.millis());

              if (paramsString.isEmpty()) {
                sb.append("?");
              } else {
                sb.append("&");
              }
              sb.append("timestamp=");
              sb.append(timestamp);
              toSign = sb.toString();
            }
            String signature = credential.sign(toSign.substring(1)); // remove "?" at the beginning
            sb.append("&signature=");
            sb.append(signature);
            paramsString = sb.toString();
          }
          return RestCall.newBuilder()
            .setUrl(buildUrlString(paramsString))
            .setMethod(method)
            .setHeaders(generateHeaders(credential))
            .build();
      }
      case POST, PUT -> {
          RestMediaBody body = JSON.createBody(getParams());
          String params = "";
          if (!credential.isAnonymous()) {
            String timestamp = Long.toString(clock.millis());
            StringBuilder payloadSB = new StringBuilder(128);
            String signedBody = body.getStringValue();
            if (signedBody.equals("{}"))
              signedBody = "";
            String payload = payloadSB
                    .append("timestamp=")
                    .append(timestamp)
                    .append(signedBody)
                    .toString();
            String signature = credential.sign(payload);
            params = new StringBuilder(64)
                    .append("?timestamp=")
                    .append(timestamp)
                    .append("&signature=")
                    .append(signature).toString();
          }
          return RestCall.newBuilder()
            .setUrl(buildUrlString(params))
            .setMethod(method)
            .setHeaders(generateHeaders(credential))
            .setBody(body)
            .build();
      }
      default -> throw new IllegalStateException(getMethod().name());
    }
  }

  @Override
  protected final void checkResult(R result, RestResponse response) throws AnyHttpException {}

  private Map<String, String> generateHeaders(ICredential credential) {
    if (credential.isAnonymous()) {
      return Map.of();
    }

    return Map.of(
            "X-MEXC-APIKEY", credential.getApiKeyId(),
            "Content-Type", "application/json");
  }

  private String buildParamsString() {
    RestParams params = getParams();
    if (params.isEmpty()) {
      return "";
    }
    return "?" + params.getQueryString(urlPathSegmentEscaper);
  }

  private String buildUrlString(String paramsString) {
    return context.getBaseUrl() + getEndpointPath() + paramsString;
  }
}
