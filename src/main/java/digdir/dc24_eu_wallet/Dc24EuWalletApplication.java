package digdir.dc24_eu_wallet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main entry point for the DC24 Wallet application
 */
@SpringBootApplication
public class Dc24EuWalletApplication {

	/**
	 * Main method to start the Spring Boot application.
	 *
	 * @param args command line arguments.
	 */
	public static void main(String[] args) {
		SpringApplication.run(Dc24EuWalletApplication.class, args);
	}
}