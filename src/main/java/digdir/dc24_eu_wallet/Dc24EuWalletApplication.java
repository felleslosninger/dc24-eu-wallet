package digdir.dc24_eu_wallet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main entry point for the DC24 Wallet application
 */
@SpringBootApplication
public class Dc24EuWalletApplication implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(Dc24EuWalletApplication.class);

	@Value("${mattr.audience}")
	private String mattrAudience;
	@Value("${mattr.tenant.url}")
	private String mattrTenantUrl;
	@Value("${mattr.issuer}")
	private String mattrIssuer;
	@Value("${ngrok.url}")
	private String ngrokUrl;
	@Value("${did.web}")
	private String didWeb;
	@Value("${template.id}")
	private String templateId;
	@Value("${domain}")
	private String domain;
	@Value("${did.web-extension}")
	private String didWebExtension;


	/**
	 * Main method to start the Spring Boot application.
	 *
	 * @param args command line arguments.
	 */
	public static void main(String[] args) {
		SpringApplication.run(Dc24EuWalletApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("MATTR_AUDIENCE: {}", mattrAudience);
		logger.info("MATTR_TENANT_URL: {}", mattrTenantUrl);
		logger.info("MATTR_ISSUER: {}", mattrIssuer);
		logger.info("NGROK_URL: {}", ngrokUrl);
		logger.info("DID_WEB: {}", didWeb);
		logger.info("TEMPLATE_ID: {}", templateId);
		logger.info("DOMAIN: {}", domain);
		logger.info("DID_WEB_EXTENSION: {}", didWebExtension);
	}
}

