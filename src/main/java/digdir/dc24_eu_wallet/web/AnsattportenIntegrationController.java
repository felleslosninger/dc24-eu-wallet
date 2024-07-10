package digdir.dc24_eu_wallet.web;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import digdir.dc24_eu_wallet.aport.VerifiableCredential;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.auth0.jwt.JWT;

import java.io.UnsupportedEncodingException;

@Controller
public class AnsattportenIntegrationController {

  private OidcUser oidcuser;

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
    this.oidcuser = oidcUser;
    return "ansattporten-authenticated";
  }

  @GetMapping("/logout/callback")
  public String logoutCallback() {
    return "logout";
  }


  @GetMapping("/informationDump")
  public String informationDump(@AuthenticationPrincipal OidcUser oidcUser) {
    DecodedJWT jwt = JWT.decode(oidcUser.getIdToken().getTokenValue());
    try {
      VerifiableCredential credential = new VerifiableCredential(oidcUser.getIdToken());
    }catch (UnsupportedEncodingException e){
      System.out.println(oidcUser.getIdToken().getTokenValue());
    }
   // return credential.printDetails();
    return "success";
  }
}

