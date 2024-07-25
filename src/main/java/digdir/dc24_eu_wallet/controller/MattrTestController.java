package digdir.dc24_eu_wallet.controller;

import digdir.dc24_eu_wallet.service.RequestService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Handles the endpoints for testing Mattr JWT and other dummy endpoints.
 */
@RequiredArgsConstructor
@RestController
public class MattrTestController {

    /**
     * Handles request logic.
     */
    private final RequestService requestService;

    /**
     * This endpoint is for checking out your Mattr jwt. If a jwt is not shown, your
     * .env file is probably wrong.
     * @return String with Mattr token.
     * @throws IOException Something went wrong while sending the request.
     */
    @GetMapping("/test")
    public String test() throws IOException {
        return "Token: " + requestService.getJwt();
    }

    /**
     * Dummy endpoint for testing purposes.
     *
     * @return a simple test String
     */
    @GetMapping("/test2")
    public String test2() {
        return "test";
    }
}
