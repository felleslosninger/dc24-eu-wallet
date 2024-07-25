package digdir.dc24_eu_wallet.component;

import digdir.dc24_eu_wallet.dto.*;
import digdir.dc24_eu_wallet.service.HttpService;
import digdir.dc24_eu_wallet.service.RequestService;

import com.google.gson.Gson;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;

/**
 * This class handle the creation, receiving and sending
 * of web cred to Mattr API and the users' wallet.
 *
 * @author Daniel Neset
 * @version 16.07.2024
 */
@Component
public class SendWebCred {
  public static final Logger logger = LoggerFactory.getLogger(SendWebCred.class);

  private final HttpService httpService;
  private final RequestService requestService;

  Gson gson = new Gson();

  @Value("${MATTR_TENANT_URL}")
  private String url;
  @Value("${DID_WEB}")
  private String didWeb;
  @Value("${DOMAIN}")
  private String domain;
  @Value("${DID_WEB_EXTENSION}")
  private String didWebExt;

  /**
   * Constructs an SendWebCred with the required services.
   *
   * @param httpService The services used to post Http data.
   * @param requestService The service fetches the access token.
   */
  @Autowired
  public SendWebCred(HttpService httpService, RequestService requestService) {
    this.httpService = httpService;
    this.requestService = requestService;
  }

  /**
   * This method is a center point that's handle creating Web credentials from
   * CredentialDTO, Encrypting them and Sending to the users' wallet.
   *
   * @param walletDID The wallet DID used to send Web credentials to.
   * @param credentialDTO The credentialDTO used to fetch the custom Credentials from.
   * @throws IOException Throws an IOException if there is something wrong with sending HTTP data.
   */
  public void createAndSendCredentials(String walletDID, CredentialDTO credentialDTO) throws IOException {
    logger.info("Starting Creation and Sending of Credentials to users wallet with DID: {}", walletDID);
    logger.info("1) Create subjects from credentialDTO");
    List<CredentialCardDTO.CredentialSubject> subjects = createSubjects(credentialDTO, walletDID);

    logger.info("2) Creating Web Credentials Cards with the Subjects data");
    List<CredentialCardDTO> credentialCardDTOS = createWebCards(subjects);

    logger.info("3) Sign the Web Credentials");
    List<CredentialSignDTO> credentialSignDTOs = signWebCredentials(credentialCardDTOS);

    logger.info("4) Gather all the Web Credentials in a object that will be encrypted");
    List<EncryptTemplateDTO> encryptPosts = encryptWebCred(credentialSignDTOs, walletDID);

    for (EncryptTemplateDTO post: encryptPosts) {
      logger.info("5) Encrypt all of the Web Credentials");
      EncryptedCredentialDTO encryptedCredentialDTO = getEncryptedCred(post);

      logger.info("6) Send the encrypted Web Credentials to the user");
      sendEncryptedCred(encryptedCredentialDTO, walletDID);
    }
  }

  /**
   * This CreateSubjects method reformat the CredentialDTO to a List of CredentialPost.CredentialSubject
   * Each CredentialSubject will be sent and made into a web-cred that will be saved
   * in a List. That list will then be encrypted and later sent to the users' wallet.
   * The good thing about this it that even if you send just a single cred or multiple, it will
   * be able to handle all instances of this.
   *
   * @param credentialDTO A list of all credentials, taken from ChallengerAndCred
   * @return Return a list of CredentialPost.CredentialSubjects
   */
  private List<CredentialCardDTO.CredentialSubject> createSubjects(CredentialDTO credentialDTO, String walletDID) {
    List<CredentialCardDTO.CredentialSubject> subjectContainers = new ArrayList<>();

    for (CredentialDTO.Cred cred: credentialDTO.getCred()) {
      CredentialCardDTO.CredentialSubject subject = new CredentialCardDTO.CredentialSubject();

      subject.setId(walletDID);
      subject.setSub(cred.getSub());
      subject.setPid(cred.getPid());

      for (CredentialDTO.AuthorizationDetails authDetails: cred.getAuthorization_details()) {
        CredentialCardDTO.AuthorizationDetails authorizationDetails = new CredentialCardDTO.AuthorizationDetails();

        authorizationDetails.setResource(authDetails.getResource());
        authorizationDetails.setType(authDetails.getType());
        authorizationDetails.setResource_name(authDetails.getResource_name());

        for (CredentialDTO.Reportees repo: authDetails.getReportees()) {
          CredentialCardDTO.Reportees reportees = new CredentialCardDTO.Reportees();

          reportees.setID(repo.getID());
          reportees.setAuthority(repo.getAuthority());
          reportees.setName(repo.getName());
          reportees.setRights(repo.getRights());

          authorizationDetails.addReportees(reportees);
        }
        subject.addAuthorization_details(authorizationDetails);
      }
      subjectContainers.add(subject);
    }
    return subjectContainers;
  }

  /**
   * Create multiple Web Credential Card with the specific subject
   * details and store them in a List.
   *
   * @param credentialSubjects A list of credentialSubjects;
   * @return Return a List of CredentialPost that are ready to Signed.
   */
  private List<CredentialCardDTO> createWebCards(List<CredentialCardDTO.CredentialSubject> credentialSubjects) {
    List<CredentialCardDTO> credentialCardDTOS = new ArrayList<>();

    for (CredentialCardDTO.CredentialSubject subject: credentialSubjects) {
      CredentialCardDTO post = new CredentialCardDTO();
      post.setProofType("Ed25519Signature2018");
      post.setTag("external-identifier");
      post.setPersist(false);
      post.setRevocable(true);
      post.setIncludeId(true);

      CredentialCardDTO.Payload payload = new CredentialCardDTO.Payload();
      payload.setName("Ansattporten Credential");
      payload.setDescription(subject.getAuthorization_details().getFirst().getReportees().getFirst().getName());
      payload.setType(List.of("AnsattportenCredential"));

      CredentialCardDTO.CredentialBranding branding = new CredentialCardDTO.CredentialBranding();
      branding.setBackgroundColor("#0a0090");
      branding.setWatermarkImageUrl("https://www.freeiconspng.com/thumbs/awesome-face-png/awesome-face-png-1.png");

      CredentialCardDTO.Issuer issuer = new CredentialCardDTO.Issuer();
      issuer.setId(didWeb);
      issuer.setName("Digdir Business Institute");
      issuer.setIconUrl("https://i.vimeocdn.com/portrait/45096260_640x640");

      payload.setCredentialSubject(subject);
      payload.setCredentialBranding(branding);
      payload.setIssuer(issuer);
      payload.setExpirationDate("2025-02-01T08:12:38.156Z");

      post.setPayload(payload);

      credentialCardDTOS.add(post);
    }
    return credentialCardDTOS;
  }

  /**
   * Sign all the Web Credentials from the Object CredentialPost.
   *
   * @param credentialCardDTOS A list of Web Credentials ready to be signed.
   * @return Return a list of signed Web Credentials.
   * @throws IOException Throws IOException if an HTTP post goes wrong.
   */
  private List<CredentialSignDTO> signWebCredentials(List<CredentialCardDTO> credentialCardDTOS) throws IOException {
    List<CredentialSignDTO> credentialSignDTOS = new ArrayList<>();

    for (CredentialCardDTO post: credentialCardDTOS) {
      String cred = gson.toJson(post);
      String responseBody = httpService.postRequest(url + "/v2/credentials/web-semantic/sign", requestService.getJwt(), cred);
      CredentialSignDTO response = gson.fromJson(responseBody, CredentialSignDTO.class);
      credentialSignDTOS.add(response);
    }
    return credentialSignDTOS;
  }

  /**
   * Encrypt all the signed Web Credentials.
   *
   * @param credentialSignDTOS All Web Credentials to be Encrypted together.
   * @param holder The wallet DID.
   * @return Return a EncryptedPost object that will be sent to the wallet.
   */
  private List<EncryptTemplateDTO> encryptWebCred(List<CredentialSignDTO> credentialSignDTOS, String holder) {
    List<EncryptTemplateDTO> encryptTemplateDTOS = new ArrayList<>();

      LinkedList<CredentialSignDTO> fifo = new LinkedList<>(credentialSignDTOS);

    while(!fifo.isEmpty()){
      String uniqueID = UUID.randomUUID().toString();
      EncryptTemplateDTO.Payload payload = new EncryptTemplateDTO.Payload();

      payload.setId(uniqueID);
      payload.setType("https://mattr.global/schemas/verifiable-credential/offer/Direct");
      payload.setTo(Collections.singletonList(holder));
      payload.setFrom(didWeb);
      payload.setCreated_time(System.currentTimeMillis() / 1000);

      EncryptTemplateDTO.Body body = new EncryptTemplateDTO.Body();

      for (int i = 0; i < 3; i++) {
        if(!fifo.isEmpty()){
          body.addCredentials(fifo.removeFirst().getCredential());
        }else{
          break;
        }
      }
      body.setDomain(domain);
      payload.setBody(body);

      EncryptTemplateDTO encryptTemplateDTO = new EncryptTemplateDTO(didWebExt, Collections.singletonList(holder), payload);
      encryptTemplateDTOS.add(encryptTemplateDTO);
    }
   return encryptTemplateDTOS;
  }

  /**
   * Gets the data that will be encrypted and encrypt it with Mattr.
   *
   * @param encryptPosts The object to be encrypted.
   * @return Return an encrypted object.
   * @throws IOException Throws IOException if it cannot send a Post HTTP request.
   */
  private EncryptedCredentialDTO getEncryptedCred(EncryptTemplateDTO encryptPosts) throws IOException {
    String encryptedPostsJson = gson.toJson(encryptPosts);
    String responseBody = httpService.postRequest(url + "/core/v1/messaging/encrypt", requestService.getJwt(), encryptedPostsJson);

    return gson.fromJson(responseBody, EncryptedCredentialDTO.class);
  }

  /**
   * Sends an encrypted credential
   *
   * @param encryptedCredentialDTO DTO containing the encrypted credential data.
   * @param holder the holder of the credential.
   * @throws IOException if there is an I/O error during the operation.
   */
  private void sendEncryptedCred(EncryptedCredentialDTO encryptedCredentialDTO, String holder) throws IOException {
    SendCredentialDTO.JweMessage jweMessage = new SendCredentialDTO.JweMessage();

    jweMessage.setProtected(encryptedCredentialDTO.getJwe().getProtectedInfo());
    jweMessage.setCiphertext(encryptedCredentialDTO.getJwe().getCiphertext());
    jweMessage.setIv(encryptedCredentialDTO.getJwe().getIv());
    jweMessage.setTag(encryptedCredentialDTO.getJwe().getTag());

    for (EncryptedCredentialDTO.Recipient recipient: encryptedCredentialDTO.getJwe().getRecipients()) {
      SendCredentialDTO.Recipient rep = getRecipient(recipient);

      jweMessage.addRecipients(rep);
    }

    SendCredentialDTO sendCredentialDTO = new SendCredentialDTO(holder, jweMessage);
    String sendMessage = gson.toJson(sendCredentialDTO);
    httpService.postRequest(url + "/core/v1/messaging/send", requestService.getJwt(), sendMessage);
  }

  private static SendCredentialDTO.Recipient getRecipient(EncryptedCredentialDTO.Recipient recipient) {
    SendCredentialDTO.Header header = new SendCredentialDTO.Header();
    SendCredentialDTO.Recipient rep = new SendCredentialDTO.Recipient();

    header.setAlg(recipient.getHeader().getAlg());
    header.setKid(recipient.getHeader().getKid());
    header.setSkid(recipient.getHeader().getSkid());

    SendCredentialDTO.Epk epk = new SendCredentialDTO.Epk();
    epk.setKty(recipient.getHeader().getEpk().getKty());
    epk.setCrv(recipient.getHeader().getEpk().getCrv());
    epk.setX(recipient.getHeader().getEpk().getX());
    header.setEpk(epk);

    rep.setHeader(header);
    rep.setEncryptedKey(recipient.getEncryptedKey());
    return rep;
  }
}