package digdir.dc24_eu_wallet.controller;

import digdir.dc24_eu_wallet.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
public class MattrTestController {

    private final RequestService requestService;

    /**
     * This endpoint is for checking out your Mattr jwt. If a jwt is not shown, your
     * .env file is probarbly wrong.
     * @return Strign with Mattr token
     * @throws IOException Something went wrong while sending the request
     */
    @GetMapping("/test")
    public String test() throws IOException {
        return "Token: " + requestService.getJwt();
    }

    // Dummy endpoint
    @GetMapping("/test2")
    public String test2() {
        return "test";
    }
}
