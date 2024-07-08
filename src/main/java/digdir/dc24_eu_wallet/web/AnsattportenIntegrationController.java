package digdir.dc24_eu_wallet.web;

import net.minidev.json.JSONObject;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;

@Controller
public class AnsattportenIntegrationController {

  private Collection claims;

  @GetMapping("/")
  public String index(){
    return "index";
  }

  @GetMapping("/ansattporten_authentication")
  public String user(Model model,
                     @AuthenticationPrincipal OidcUser oidcUser) {
    model.addAttribute("idtoken", oidcUser.getIdToken().getTokenValue());
    model.addAttribute("pid", oidcUser.getUserInfo().getClaim("pid"));
    model.addAttribute("authorizationdetails", oidcUser.getUserInfo().getClaim("authorization_details"));
    model.addAttribute("name", oidcUser.getFullName());
    setClaims(oidcUser);
    return "ansattporten-authenticated";
  }

  @GetMapping("/logout/callback")
  public String logoutCallback() {
    return "logout";
  }

  public void setClaims(OidcUser user){
    this.claims = claims;
  }

  public Collection getClaims(){
    return claims;
  }



}