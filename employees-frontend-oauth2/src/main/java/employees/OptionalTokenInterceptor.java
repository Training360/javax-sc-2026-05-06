package employees;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

import java.io.IOException;

@RequiredArgsConstructor
public class OptionalTokenInterceptor implements ClientHttpRequestInterceptor {

    private final OAuth2AuthorizedClientManager authorizedClientManager;

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        if (authentication instanceof OAuth2AuthenticationToken oauth2) {
            OAuth2AuthorizedClient client =
                    authorizedClientManager.authorize(
                            OAuth2AuthorizeRequest
                                    .withClientRegistrationId(oauth2.getAuthorizedClientRegistrationId())
                                    .principal(oauth2)
                                    .build());

            if (client != null &&
                    client.getAccessToken() != null) {

                request.getHeaders().setBearerAuth(
                        client.getAccessToken().getTokenValue());
            }
        }

        return execution.execute(request, body);
    }
}
