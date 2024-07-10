package digdir.dc24_eu_wallet;

import digdir.dc24_eu_wallet.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TestController {
    final RequestService requestService;

    @GetMapping("/test")
    public String test() {

        return "Token: ";
    }
}
