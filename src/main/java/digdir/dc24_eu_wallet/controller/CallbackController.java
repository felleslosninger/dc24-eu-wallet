package digdir.dc24_eu_wallet.controller;

import com.google.gson.Gson;
import digdir.dc24_eu_wallet.component.SendWebCred;
import digdir.dc24_eu_wallet.dto.CredentialDTO;
import digdir.dc24_eu_wallet.dto.PresentationDTO;
import digdir.dc24_eu_wallet.entities.Challengers;
import digdir.dc24_eu_wallet.service.ChallengersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * Controller for handling callback post requests.
 * It provides a single endpoint that the Mattr
 * API can reach.
 *
 * @author Daniel Neset
 * @version 16.07.2024
 */
@RestController
@RequestMapping("callback")
public class CallbackController {
  public static final Logger logger = LoggerFactory.getLogger(CallbackController.class);
  Gson gson = new Gson();
  private final ChallengersService challengersService;
  private final SendWebCred sendWebCred;

  /**
   * Constructs an CallbackController with the required services.
   *
   * @param challengersService The services used to manage the Challengers.
   * @param sendWebCred The service used to send the Web Credentials.
   */
  @Autowired
  public CallbackController(ChallengersService challengersService, SendWebCred sendWebCred){
    this.challengersService = challengersService;
    this.sendWebCred = sendWebCred;
  }

  /**
   * Handle the callback request from the Mattr API.
   * Checks the db to see if there is data with that challenger ID
   * and send it and the users wallet DID to  {@link SendWebCred}
   *
   * @param presentationDTO A PresentationDTO object containing the user wallet DID and Challenger ID.
   * @return Return a ResponseEntity with PresentationDTO data and HttpStatus.
   * @throws IOException Throws and IOE exception if there is a problem with sending or receiving data.
   */
  @PostMapping()
  public ResponseEntity<PresentationDTO> postCallback(@RequestBody PresentationDTO presentationDTO) throws IOException {
    String holder = presentationDTO.getHolder();
    String challenger = presentationDTO.getChallengeId();
    ResponseEntity<PresentationDTO> response;

    System.out.println(gson.toJson(presentationDTO));

    if((holder.isEmpty() || holder.isBlank())){
      response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }else if((challenger.isEmpty() || challenger.isBlank())){
      response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }else{
      logger.info("Callback from Mattr with challengerId: {}", presentationDTO.getChallengeId());
      logger.info("Fetch object from database with the same ChallengerId: {}", presentationDTO.getChallengeId());
      Challengers challengers = challengersService.getRecord(presentationDTO.getChallengeId());
      CredentialDTO credentialDTO = gson.fromJson(challengers.getJsonData(), CredentialDTO.class);
      sendWebCred.createAndSendCredentials(presentationDTO.getHolder(), credentialDTO);
      response = new ResponseEntity<>(HttpStatus.OK);
    }

    return response;
  }

}
