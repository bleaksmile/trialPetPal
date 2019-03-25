package trialpetpal.demo.oauthSecurity;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;
import trialpetpal.demo.users.models.ParentUser;
import trialpetpal.demo.users.repositories.MainUserRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

import static trialpetpal.demo.oauthSecurity.Constants.homeUrl;


@Component
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

  @Autowired
  private MainUserRepository userRepository;

  @Autowired
  private JwtTokenUtil jwtTokenUtil;

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
    if (response.isCommitted()) {
      return;
    }
    DefaultOidcUser oidcUser = (DefaultOidcUser) authentication.getPrincipal();
    Map<String, Object> attributes = oidcUser.getAttributes();
    String email = (String) attributes.get("email");
    ParentUser user = userRepository.findByEmail(email);
    String token = jwtTokenUtil.generateToken(user);
    String redirectionUrl = UriComponentsBuilder.fromUriString(homeUrl)
            .queryParam("auth_token", token)
            .build().toUriString();
    getRedirectStrategy().sendRedirect(request, response, redirectionUrl);
  }

}
