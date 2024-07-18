package digdir.dc24_eu_wallet.web;

import digdir.dc24_eu_wallet.aport.toMattr.JsonDataToMattr;
import digdir.dc24_eu_wallet.aport.fromAnsattporten.TokenPayload;
import digdir.dc24_eu_wallet.component.SendWebCred;
import digdir.dc24_eu_wallet.service.HttpService;
import digdir.dc24_eu_wallet.service.RequestService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller for handling integration with Ansattporten.

@Controller
public class AnsattportenIntegrationController {


  /**
   * Handles requests to the root URL ("/").
   *
   * @return the "index" view name.



  @GetMapping("/")
  public String index(){
    return "index";
  }

  /**
   * Handles requests to the Ansattporten authentication URL ("/ansattporten_authentication").
   * Retrieves OIDC user information and adds it to the model.
   *
   * @param model the model to add attributes to.
   * @param oidcUser the authenticated OIDC user.
   * @return the "ansattporten-authenticated" view name.
   */

  /**
  @GetMapping("/ansattporten_authentication")
  public String user(Model model,
                     @AuthenticationPrincipal OidcUser oidcUser) {
    model.addAttribute("idtoken", oidcUser.getIdToken().getTokenValue());
    model.addAttribute("pid", oidcUser.getUserInfo().getClaim("pid"));
    model.addAttribute("authorizationdetails", oidcUser.getUserInfo().getClaim("authorization_details"));
    model.addAttribute("name", oidcUser.getFullName());
    return "ansattporten-authenticated";
  }

  /**
   * Handles requests to the logout callback URL ("/logout/callback").
   *
   * @return the "logout" view name.

  @GetMapping("/logout/callback")
  public String logoutCallback() {
    return "logout";
  }


  @GetMapping("/informationDump")
  public String informationDump(@AuthenticationPrincipal OidcUser oidcUser) {
    TokenPayload credential = new TokenPayload(oidcUser.getIdToken());
    JsonDataToMattr testing = new JsonDataToMattr(credential);



    return "success";
  }
}
*/
