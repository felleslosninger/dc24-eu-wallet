package digdir.dc24_eu_wallet.dto;

import java.util.ArrayList;
import java.util.List;

public class EncryptPost {
  private String senderDidUrl;
  private List<String> recipientDidUrls;
  private Payload payload;

  public EncryptPost(String senderDidUrl, List<String> recipientDidUrls, Payload payload) {
    this.senderDidUrl = senderDidUrl;
    this.recipientDidUrls = recipientDidUrls;
    this.payload = payload;
  }

  public static class Payload {
    private String id;
    private String type;
    private List<String> to;
    private String from;
    private long created_time;
    private Body body;

    public Body getBody() {
      return body;
    }

    public void setBody(Body body) {
      this.body = body;
    }

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    public String getType() {
      return type;
    }

    public void setType(String type) {
      this.type = type;
    }

    public List<String> getTo() {
      return to;
    }

    public void setTo(List<String> to) {
      this.to = to;
    }

    public String getFrom() {
      return from;
    }

    public void setFrom(String from) {
      this.from = from;
    }

    public long getCreated_time() {
      return created_time;
    }

    public void setCreated_time(long created_time) {
      this.created_time = created_time;
    }




  }




  public static class Body{
    private List<CredentialResponse.Credential> credentials = new ArrayList<>();
    private String domain;


    public List<CredentialResponse.Credential> getCredentials() {
      return credentials;
    }

    public void setCredentials(List<CredentialResponse.Credential> credentials) {
      this.credentials = credentials;
    }

    public void addCredentials(CredentialResponse.Credential credential) {
      this.credentials.add(credential);
    }

    public String getDomain() {
      return domain;
    }

    public void setDomain(String domain) {
      this.domain = domain;
    }
  }




  public String getSenderDidUrl() {
    return senderDidUrl;
  }

  public void setSenderDidUrl(String senderDidUrl) {
    this.senderDidUrl = senderDidUrl;
  }

  public List<String> getRecipientDidUrls() {
    return recipientDidUrls;
  }

  public void setRecipientDidUrls(List<String> recipientDidUrls) {
    this.recipientDidUrls = recipientDidUrls;
  }

  public Payload getPayload() {
    return payload;
  }

  public void setPayload(Payload payload) {
    this.payload = payload;
  }
}
