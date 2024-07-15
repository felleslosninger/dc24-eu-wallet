package digdir.dc24_eu_wallet.component;

import com.google.gson.Gson;
import digdir.dc24_eu_wallet.dto.*;
import digdir.dc24_eu_wallet.service.HttpService;
import digdir.dc24_eu_wallet.service.RequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;

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

  @Autowired
  public SendWebCred(HttpService httpService, RequestService requestService) {
    this.httpService = httpService;
    this.requestService = requestService;
  }

  /**
   *
   * Each ChallengerAndCred can contain multiple creds
   * Here we should split them and create multiple web creds
   * This can be used in sending multiple with the encrypt method
   *
   */


  public void testMain(String holder, ChallengerAndCred cred) throws IOException {


    //Create all the CredentialSubjects:
    List<CredentialPost.CredentialSubject> subjects = test(cred, holder);

    //Create cards from these:
    List<CredentialPost> credentialPosts = createCard(subjects);

    //Create a list of web credentials
    List<CredentialResponse> credentialResponses = createWebCred(credentialPosts);

    //Encrypt the list of web credentials
    EncryptPost encryptPosts = encryptWebCred(credentialResponses, holder);

    //Create encrypted cred

  }



  /**
   * This test class reformat the Credentials to a List of CredentialSubject
   * Each CredentialSubject will be sent and made into a web-cred that will be saved
   * in a List that will then put them all in a encryption and then sent to the user
   *
   * The good thing about this it that even if you send just a single cred or many, it will
   * be able to handle all of them.
   *
   * @param credList A list of all credentials, taken from ChallengerAndCred
   * @return Return a list of CredentialPost.CredentialSubjects
   */
  public List<CredentialPost.CredentialSubject> test(ChallengerAndCred credList, String holder){

    List<CredentialPost.CredentialSubject> containers = new ArrayList<>();

    for (ChallengerAndCred.Cred cred: credList.getCred()) {

      CredentialPost.CredentialSubject subject = new CredentialPost.CredentialSubject();
      subject.setId(holder);
      subject.setSub(cred.getSub());
      subject.setPid(cred.getPid());


      for (ChallengerAndCred.AuthorizationDetails authDetails: cred.getAuthorization_details()) {
        CredentialPost.AuthorizationDetails authorizationDetails = new CredentialPost.AuthorizationDetails();

        authorizationDetails.setResource(authDetails.getResource());
        authorizationDetails.setType(authDetails.getType());
        authorizationDetails.setResource_name(authDetails.getResource_name());

        for (ChallengerAndCred.Reportees repo: authDetails.getReportees()) {
          CredentialPost.Reportees reportees = new CredentialPost.Reportees();

          reportees.setID(repo.getID());
          reportees.setAuthority(repo.getAuthority());
          reportees.setName(repo.getName());
          reportees.setRights(repo.getRights());

          authorizationDetails.addReportees(reportees);
        }
        subject.addAuthorization_details(authorizationDetails);
      }

    }

    return containers;

  }





  private List<CredentialPost> createCard(List<CredentialPost.CredentialSubject> credentialSubjects){

    List<CredentialPost> credentialPosts = new ArrayList<>();
    for (CredentialPost.CredentialSubject subject: credentialSubjects) {

      CredentialPost post = new CredentialPost();
      post.setProofType("Ed25519Signature2018");
      post.setTag("external-identifier");
      post.setPersist(false);
      post.setRevocable(true);
      post.setIncludeId(true);

      CredentialPost.Payload payload = new CredentialPost.Payload();
      payload.setName("DigDir Credential");
      payload.setDescription("Issued by Trusted Digdir");
      payload.setType(List.of("AnsattportenCredential"));

      CredentialPost.CredentialBranding branding = new CredentialPost.CredentialBranding();
      branding.setBackgroundColor("#0a0090");
      branding.setWatermarkImageUrl("https://www.freeiconspng.com/thumbs/awesome-face-png/awesome-face-png-1.png");

      CredentialPost.Issuer issuer = new CredentialPost.Issuer();
      issuer.setId("did:web:daniel-neset-xjbzrt.vii.au01.mattr.global");
      issuer.setName("Zealopia Business Institute");
      issuer.setIconUrl("https://i.vimeocdn.com/portrait/45096260_640x640");

      payload.setCredentialSubject(subject);
      payload.setCredentialBranding(branding);
      payload.setIssuer(issuer);
      payload.setExpirationDate("2025-02-01T08:12:38.156Z");

      post.setPayload(payload);

      credentialPosts.add(post);
    }
    return credentialPosts;
  }

  public List<CredentialResponse> createWebCred(List<CredentialPost> credentialPosts) throws IOException {

    List<CredentialResponse> credentialResponses = new ArrayList<>();

    for (CredentialPost post: credentialPosts) {
      String cred = gson.toJson(post);
      String responseBody = httpService.postRequest(url + "/v2/credentials/web-semantic/sign", requestService.getJwt(), cred);

      CredentialResponse response = gson.fromJson(responseBody, CredentialResponse.class);
      credentialResponses.add(response);
    }

    return credentialResponses;
  }

  public EncryptPost encryptWebCred(List<CredentialResponse> credentialResponses, String holder){

    String uniqueID = UUID.randomUUID().toString();
    EncryptPost.Payload payload = new EncryptPost.Payload();

    payload.setId(uniqueID);
    payload.setType("https://mattr.global/schemas/verifiable-credential/offer/Direct");
    payload.setTo(Collections.singletonList(holder));
    payload.setFrom(didWeb);
    payload.setCreated_time(System.currentTimeMillis() / 1000);

    EncryptPost.Body body = new EncryptPost.Body();
    for (CredentialResponse cred: credentialResponses) {
      body.addCredentials(cred.getCredential());
    }
    body.setDomain(domain);
    payload.setBody(body);
    EncryptPost encryptPost = new EncryptPost(didWebExt, Collections.singletonList(holder), payload);

    return encryptPost;

  }

  public void sendEncryptedCred(List<EncryptPost> encryptPosts) throws IOException {


    String newJson = null;



    String responseBody2 = httpService.postRequest(url + "/core/v1/messaging/encrypt", requestService.getJwt(), newJson);



  }


}
