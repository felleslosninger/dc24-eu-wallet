package digdir.dc24_eu_wallet.controller;

import com.google.gson.Gson;
import digdir.dc24_eu_wallet.dto.PresentationDTO;
import digdir.dc24_eu_wallet.service.ChallengersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("callback")
public class CallbackController {

  public static final Logger logger = LoggerFactory.getLogger(PresentationController.class);

  private final ChallengersService challengersService;

  @Autowired
  public CallbackController(ChallengersService challengersService){
    this.challengersService = challengersService;
  }


  @PostMapping()
  public ResponseEntity<PresentationDTO> postCallback(@RequestBody PresentationDTO wallet){

    Gson gson = new Gson();

    ResponseEntity<PresentationDTO> response;
    logger.info("Callback from user with holder did: {}", wallet.getHolder());
    logger.info("Callback from user with challenge did: {}", wallet.getChallengeId());

//    Records records = recordsService.getRecord(wallet.getChallengeId());

  //  System.out.println(records.getJsonData());

  //  ChallengerAndCred challengerAndCred = gson.fromJson(records.getJsonData(), ChallengerAndCred.class);

 //   sendWebCred.sendPostRequest(wallet.getHolder(), challengerAndCred, url, token);
    response = new ResponseEntity<>(HttpStatus.OK);

    return response;
  }


}
