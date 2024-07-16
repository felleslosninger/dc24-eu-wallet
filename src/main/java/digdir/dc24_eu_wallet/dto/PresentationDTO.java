package digdir.dc24_eu_wallet.dto;

import java.util.List;
import java.util.Map;

/**
 * Hold Presentation data witch we get back from
 * Mattr API callback.
 *
 * @author Daniel Neset
 * @version 16.07.2024
 */
public class PresentationDTO {

  private String presentationType;
  private String challengeId;
  private Map<String, Object> claims;
  private boolean verified;
  private String holder;
  private Presentation presentation;

  public String getPresentationType() {
    return presentationType;
  }

  public void setPresentationType(String presentationType) {
    this.presentationType = presentationType;
  }

  public String getChallengeId() {
    return challengeId;
  }

  public void setChallengeId(String challengeId) {
    this.challengeId = challengeId;
  }

  public Map<String, Object> getClaims() {
    return claims;
  }

  public void setClaims(Map<String, Object> claims) {
    this.claims = claims;
  }

  public boolean isVerified() {
    return verified;
  }

  public void setVerified(boolean verified) {
    this.verified = verified;
  }

  public String getHolder() {
    return holder;
  }

  public void setHolder(String holder) {
    this.holder = holder;
  }

  public Presentation getPresentation() {
    return presentation;
  }

  public void setPresentation(Presentation presentation) {
    this.presentation = presentation;
  }

  public static class Presentation{
    private List<String> context;
    private List<String> type;
    private String holder;
    private List<Proof> proof;

    public List<String> getContext() {
      return context;
    }

    public void setContext(List<String> context) {
      this.context = context;
    }

    public List<String> getType() {
      return type;
    }

    public void setType(List<String> type) {
      this.type = type;
    }

    public String getHolder() {
      return holder;
    }

    public void setHolder(String holder) {
      this.holder = holder;
    }

    public List<Proof> getProof() {
      return proof;
    }

    public void setProof(List<Proof> proof) {
      this.proof = proof;
    }
  }

  public static class Proof{
    private String type;
    private String created;
    private String verificationMethod;
    private String proofPurpose;
    private String challenge;
    private String domain;
    private String jws;

    public String getType() {
      return type;
    }

    public void setType(String type) {
      this.type = type;
    }

    public String getCreated() {
      return created;
    }

    public void setCreated(String created) {
      this.created = created;
    }

    public String getVerificationMethod() {
      return verificationMethod;
    }

    public void setVerificationMethod(String verificationMethod) {
      this.verificationMethod = verificationMethod;
    }

    public String getProofPurpose() {
      return proofPurpose;
    }

    public void setProofPurpose(String proofPurpose) {
      this.proofPurpose = proofPurpose;
    }

    public String getChallenge() {
      return challenge;
    }

    public void setChallenge(String challenge) {
      this.challenge = challenge;
    }

    public String getDomain() {
      return domain;
    }

    public void setDomain(String domain) {
      this.domain = domain;
    }

    public String getJws() {
      return jws;
    }

    public void setJws(String jws) {
      this.jws = jws;
    }
  }

}


