package digdir.dc24_eu_wallet.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import digdir.dc24_eu_wallet.idTokens.TokenPayload;
import digdir.dc24_eu_wallet.idTokens.toMattr.MattrObjectHead;
import digdir.dc24_eu_wallet.dto.CredentialDTO;
import digdir.dc24_eu_wallet.dto.PresentationRequestDTO;
import digdir.dc24_eu_wallet.dto.PresentationResponseDTO;
import digdir.dc24_eu_wallet.entities.Challengers;
import digdir.dc24_eu_wallet.service.ChallengersService;
import digdir.dc24_eu_wallet.service.HttpService;
import digdir.dc24_eu_wallet.service.RequestService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.UUID;

/**
 * Here we can send in JSON data and get a QR code in return. This is also linked to the
 * database and Mattr.
 *
 * @author Daniel Neset, Solveig Langbakk & Elise Strand Bråtveit
 * @version 19.07.2024
 */
@Controller
public class PresentationController {

  public static final Logger logger = LoggerFactory.getLogger(PresentationController.class);
  private final ChallengersService challengersService;
  private final HttpService httpService;
  private final RequestService requestService;

  Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

  @Value("${MATTR_TENANT_URL}")
  private String url;
  @Value("${NGROK_URL}")
  private String ngrok;
  @Value("${DID_WEB}")
  private String didWeb;
  @Value("${TEMPLATE_ID}")
  private String templateId;

  /**
   * Constructs an CallbackController with the required services.
   *
   * @param challengersService The services used to manage the Challengers.
   * @param httpService The services are used to send post requests.
   * @param requestService The service is used to fetch the access token.
   */
  @Autowired
  public PresentationController(ChallengersService challengersService, HttpService httpService, RequestService requestService) {
    this.challengersService = challengersService;
    this.httpService = httpService;
    this.requestService = requestService;
  }

  /**
   * Handles requests to the root URL ("/").
   *
   * @return the "index" view name.
   */
  @GetMapping("/")
  public String index() {
    return "index";
  }

  /**
   * Handles requests to the Ansattporten and IDporten authentication URL ("/idporten_authenticatio").
   * Retrieves OIDC user information and adds it to the model.
   *
   * @param model the model to add attributes to.
   * @param oidcUser the authenticated OIDC user.
   * @return the "idporten_authentication" view name.
   */
  @GetMapping("/idporten_authentication")
  public String user(Model model,
                     @AuthenticationPrincipal OidcUser oidcUser) {
    model.addAttribute("idtoken", oidcUser.getIdToken().getTokenValue());
    model.addAttribute("pid", oidcUser.getUserInfo().getClaim("pid"));
    model.addAttribute("authorizationdetails", oidcUser.getUserInfo().getClaim("authorization_details"));
    model.addAttribute("name", oidcUser.getFullName());
    
    model.addAttribute("qrCode", getQR(oidcUser));
    
    return "idporten_authentication";
  }

  /**
   * Takes the token content from the logged in oidc user in ansattporten or Idporten, and creates an object out of it
   * that is used to format a new object, which is used to construct a json string on the correct format of
   * the request MATTR need. The string will contain the information about the person that is supposed to et
   * rights/accesses, as well as which rights/accesses that person should get.
   *
   * @param oidcUser logged in ansattporten or Idporten oidc user
   * @return string with JSON data ready to send to MATTR
   */
  public String getJsonContentForMattr(OidcUser oidcUser) {
    TokenPayload credential = new TokenPayload(oidcUser.getIdToken());
    String token = credential.getTokenPayloadAsString();
    MattrObjectHead head = new MattrObjectHead(credential.getTokenHeadAnsattporten(token));
    return head.getFormattedJsonData();
  }

  /**
   * Takes the relevant information MATTR needs for issuance from the id token of the logged in ansattporten user or Idport user,
   * and creates a qr code that a user of the MATTR wallet can scan in order to get issued the different rights that
   * person has in ansattporten or Idporten. The qr code will contain information about which person these rights/accesses should
   * be assigned to, as well as which rights/accesses it is about.
   *
   * @param oidcUser logged in oidc user on ansattporten or Idporten
   * @return url to qr code as string
   */
  public String getQR(@AuthenticationPrincipal OidcUser oidcUser) {

    String jsoncontent;
    String qrCode = "";

    jsoncontent = getJsonContentForMattr(oidcUser);
    CredentialDTO credentialDTO = gson.fromJson(jsoncontent, CredentialDTO.class);

    logger.info("New PostRequest to create a Presentation Request");
   
    if (credentialDTO.isValid()){
      logger.info("New PostRequest has a valid body.");
      String uniqueID = UUID.randomUUID().toString();

      Challengers challengers = new Challengers();
      challengers.setChallenger(uniqueID);
      challengers.setJsonData(gson.toJson(credentialDTO));

      logger.info("Saving Presentation Credential to the database with ChallengerID: {}", uniqueID);
      challengersService.saveChallenger(challengers);

      logger.info("Sending request to be formatted as a QR code.");
      qrCode = sendPresentationRequest(uniqueID);
      logger.info("QR CODE: {}", qrCode);

    } else {
      logger.warn("The Json Body is not Valid!");
    }
    return qrCode;
  }

  /**
   * Helper class to create a Presentation Request and
   * format it into an QR-code link.
   *
   * @param challenger The unique challenger id.
   * @return Return a Link to a QR-CODE.
   */
  private String sendPresentationRequest(String challenger) {
    logger.info("Creating PresentationRequest to be sent to Mattr");
    PresentationRequestDTO presentationRequestDTO = new PresentationRequestDTO();
    presentationRequestDTO.setChallenge(challenger);
    presentationRequestDTO.setDid(didWeb);
    presentationRequestDTO.setTemplateId(templateId);
    presentationRequestDTO.setCallbackUrl(ngrok);
    String body = gson.toJson(presentationRequestDTO);

    String response = null;
    try {
      logger.info("Making request");
      response = httpService.postRequest(url + "/v2/credentials/web-semantic/presentations/requests", requestService.getJwt(), body);
      PresentationResponseDTO presentationResponseDTO = gson.fromJson(response, PresentationResponseDTO.class);
      response = "https://api.qrserver.com/v1/create-qr-code/?size=300x300&data=" + presentationResponseDTO.getDidcommUri();
    } catch (IOException ioException) {
      logger.warn("Cannot create Presentation Request!");
    }
    return response;
  }

  /**
   * Handles requests to the logout callback URL ("/logout/callback").
   *
   * @return the "logout" view name.
   */
  @GetMapping("/logout/callback")
  public String logoutCallback() {
    return "logout";
  }
}
