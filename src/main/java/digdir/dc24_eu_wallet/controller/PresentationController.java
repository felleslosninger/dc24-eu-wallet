package digdir.dc24_eu_wallet.controller;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import digdir.dc24_eu_wallet.dto.ChallengerAndCred;
import digdir.dc24_eu_wallet.dto.PresentationRequest;
import digdir.dc24_eu_wallet.dto.PresentationResponse2;
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

@RestController
@RequestMapping("Presentation")
@Component
public class PresentationController {

  public static final Logger logger = LoggerFactory.getLogger(PresentationController.class);

  private final ChallengersService challengersService;
  private final HttpService httpService;
  private final RequestService requestService;

  Gson gson = new GsonBuilder()
          .excludeFieldsWithoutExposeAnnotation()
          .create();

  @Value("${MATTR_TENANT_URL}")
  private String url;
  @Value("${NGROK_URL}")
  private String ngrok;
  @Value("${DID_WEB}")
  private String didWeb;
  @Value("${TEMPLATE_ID}")
  private String templateId;

  @Autowired
  public PresentationController(ChallengersService challengersService, HttpService httpService, RequestService requestService){
    this.challengersService = challengersService;
    this.httpService = httpService;
    this.requestService = requestService;
  }

  @PostMapping
  public ResponseEntity<ChallengerAndCred> postAPI(@RequestBody ChallengerAndCred wallet) {

    ResponseEntity<ChallengerAndCred> response;
    logger.info("New PostRequest to create a Presentation Request");

    if(wallet.isValid()){
      logger.info("New PostRequest has a valid body.");
      String uniqueID = UUID.randomUUID().toString();

      Challengers challengers = new Challengers();
      challengers.setChallenger(uniqueID);
      challengers.setJsonData(gson.toJson(wallet));

      logger.info("Saving Presentation Cred Data to the database");
      challengersService.saveChallenger(challengers);

      logger.info("Sending request to be formatted as a QR code.");
      sendPresentationRequest(uniqueID);

      response = new ResponseEntity<>(HttpStatus.OK);

    }else{
      logger.warn("The Json Body is not Valid!");
      response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    return response;
  }

  private String sendPresentationRequest(String challenger){

    PresentationRequest presentationRequest = new PresentationRequest();
    presentationRequest.setChallenge(challenger);
    presentationRequest.setDid(didWeb);
    presentationRequest.setTemplateId(templateId);
    presentationRequest.setCallbackUrl(ngrok);

    String response = null;
    try {
      response = httpService.postRequest(url + "/v2/credentials/web-semantic/presentations/requests", requestService.getJwt(), gson.toJson(presentationRequest));
    }catch (IOException ioException){
      System.out.println("hehe");
    }

    //Gson gson = new Gson();
    PresentationResponse2 presentationResponse2 = gson.fromJson(response, PresentationResponse2.class);

    System.out.println(response);
    System.out.println("url: https://api.qrserver.com/v1/create-qr-code/?size=300x300&data=" + presentationResponse2.getDidcommUri());
    return response;
  }



}
