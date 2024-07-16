package digdir.dc24_eu_wallet.controller;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.UUID;

/**
 * Template Post endpoint where we can send in JSON data and
 * get a QR code in return. This is also linked to the
 * database and Mattr.
 *
 * @author Daniel Neset
 * @version 16.07.2024
 */
@RestController
@RequestMapping("Presentation")
@Component
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
  public PresentationController(ChallengersService challengersService, HttpService httpService, RequestService requestService){
    this.challengersService = challengersService;
    this.httpService = httpService;
    this.requestService = requestService;
  }

  /**
   * Controller for managing creation of {@Link CredentialDTO}.
   * The CredentialDTO is stored in the database with a unique
   * Challenger ID that is also sent with the Mattr API Callback.
   * The challenger ID is later used to identify what data the user
   * should get.
   *
   * @param credentialDTO The credentialDTO object sent in.
   * @return Return ResponseEntity with credentialDTO object data and HttpStatus
   */
  @PostMapping
  public ResponseEntity<String> postAPI(@RequestBody CredentialDTO credentialDTO) {
    logger.info("New PostRequest to create a Presentation Request");
    ResponseEntity<String> response;

    if(credentialDTO.isValid()){
      logger.info("New PostRequest has a valid body.");
      String uniqueID = UUID.randomUUID().toString();

      Challengers challengers = new Challengers();
      challengers.setChallenger(uniqueID);
      challengers.setJsonData(gson.toJson(credentialDTO));

      logger.info("Saving Presentation Credential to the database with ChallengerID: {}", uniqueID);
      challengersService.saveChallenger(challengers);

      logger.info("Sending request to be formatted as a QR code.");
      String qrCode = sendPresentationRequest(uniqueID);
      logger.info("QR CODE: {}", qrCode);


      response = new ResponseEntity<>(qrCode, HttpStatus.OK);
    }else{
      logger.warn("The Json Body is not Valid!");
      response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    return response;
  }

  /**
   * Helper class to create a Presentation Request and
   * format it into an QR-code link.
   *
   * @param challenger The unique challenger id.
   * @return Return a Link to a QR-CODE.
   */
  private String sendPresentationRequest(String challenger){

    logger.info("Creating PresentationRequest to be sent to Mattr");
    PresentationRequestDTO presentationRequestDTO = new PresentationRequestDTO();
    presentationRequestDTO.setChallenge(challenger);
    presentationRequestDTO.setDid(didWeb);
    presentationRequestDTO.setTemplateId(templateId);
    presentationRequestDTO.setCallbackUrl(ngrok);
    String body = gson.toJson(presentationRequestDTO);

    String response = null;
    try {
      System.out.println("Making request");
      response = httpService.postRequest(url + "/v2/credentials/web-semantic/presentations/requests", requestService.getJwt(), body);
      PresentationResponseDTO presentationResponseDTO = gson.fromJson(response, PresentationResponseDTO.class);
      response = "url: https://api.qrserver.com/v1/create-qr-code/?size=300x300&data=" + presentationResponseDTO.getDidcommUri();
    }catch (IOException ioException){
      logger.warn("Cannot create Presentation Request!");
    }

    return response;
  }

}
