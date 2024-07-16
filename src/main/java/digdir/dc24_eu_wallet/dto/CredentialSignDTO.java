package digdir.dc24_eu_wallet.dto;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class CredentialSignDTO {
  private String id;
  private String tag;
  private Credential credential;
  private CredentialStatus credentialStatus;
  private String issuanceDate;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getTag() {
    return tag;
  }

  public void setTag(String tag) {
    this.tag = tag;
  }

  public Credential getCredential() {
    return credential;
  }

  public void setCredential(Credential credential) {
    this.credential = credential;
  }

  public CredentialStatus getCredentialStatus() {
    return credentialStatus;
  }

  public void setCredentialStatus(CredentialStatus credentialStatus) {
    this.credentialStatus = credentialStatus;
  }

  public String getIssuanceDate() {
    return issuanceDate;
  }

  public void setIssuanceDate(String issuanceDate) {
    this.issuanceDate = issuanceDate;
  }

  public static class Credential {
    private String id;
    private List<String> type;
    private Issuer issuer;
    private String name;
    private String description;
    private CredentialBranding credentialBranding;
    private String expirationDate;
    private String issuanceDate;
    private CredentialSubject credentialSubject;
    @SerializedName("@context")
    private List<String> context;
    private CredentialStatus credentialStatus;
    private Proof proof;

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    public List<String> getType() {
      return type;
    }

    public void setType(List<String> type) {
      this.type = type;
    }

    public Issuer getIssuer() {
      return issuer;
    }

    public void setIssuer(Issuer issuer) {
      this.issuer = issuer;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getDescription() {
      return description;
    }

    public void setDescription(String description) {
      this.description = description;
    }

    public CredentialBranding getCredentialBranding() {
      return credentialBranding;
    }

    public void setCredentialBranding(CredentialBranding credentialBranding) {
      this.credentialBranding = credentialBranding;
    }

    public String getExpirationDate() {
      return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
      this.expirationDate = expirationDate;
    }

    public String getIssuanceDate() {
      return issuanceDate;
    }

    public void setIssuanceDate(String issuanceDate) {
      this.issuanceDate = issuanceDate;
    }

    public CredentialSubject getCredentialSubject() {
      return credentialSubject;
    }

    public void setCredentialSubject(CredentialSubject credentialSubject) {
      this.credentialSubject = credentialSubject;
    }

    public List<String> getContext() {
      return context;
    }

    public void setContext(List<String> context) {
      this.context = context;
    }

    public CredentialStatus getCredentialStatus() {
      return credentialStatus;
    }

    public void setCredentialStatus(CredentialStatus credentialStatus) {
      this.credentialStatus = credentialStatus;
    }

    public Proof getProof() {
      return proof;
    }

    public void setProof(Proof proof) {
      this.proof = proof;
    }
  }

  public static class CredentialBranding {
    private String backgroundColor;
    private String watermarkImageUrl;

    public String getBackgroundColor() {
      return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
      this.backgroundColor = backgroundColor;
    }

    public String getWatermarkImageUrl() {
      return watermarkImageUrl;
    }

    public void setWatermarkImageUrl(String watermarkImageUrl) {
      this.watermarkImageUrl = watermarkImageUrl;
    }
  }

  public static class Issuer {
    private String id;
    private String name;
    private String logoUrl;
    private String iconUrl;

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getLogoUrl() {
      return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
      this.logoUrl = logoUrl;
    }

    public String getIconUrl() {
      return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
      this.iconUrl = iconUrl;
    }
  }

  public static class CredentialSubject {

    private String id;
    private String sub;
    private String pid;
    private List<CredentialCardDTO.AuthorizationDetails> authorization_details;

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    public String getSub() {
      return sub;
    }

    public void setSub(String sub) {
      this.sub = sub;
    }

    public String getPid() {
      return pid;
    }

    public void setPid(String pid) {
      this.pid = pid;
    }

    public List<CredentialCardDTO.AuthorizationDetails> getAuthorization_details() {
      return authorization_details;
    }

    public void setAuthorization_details(List<CredentialCardDTO.AuthorizationDetails> authorization_details) {
      this.authorization_details = authorization_details;
    }
  }

  public static class AuthorizationDetails {
    private String resource;
    private String type;
    private String resource_name;
    private List<CredentialCardDTO.Reportees> reportees = new ArrayList<>();

    public String getResource() {
      return resource;
    }

    public void setResource(String resource) {
      this.resource = resource;
    }

    public String getType() {
      return type;
    }

    public void setType(String type) {
      this.type = type;
    }

    public String getResource_name() {
      return resource_name;
    }

    public void setResource_name(String resource_name) {
      this.resource_name = resource_name;
    }

    public List<CredentialCardDTO.Reportees> getReportees() {
      return reportees;
    }

    public void setReportees(List<CredentialCardDTO.Reportees> reportees) {
      this.reportees = reportees;
    }
    public void addReportees(CredentialCardDTO.Reportees reportees) {
      this.reportees.add(reportees);
    }
  }

  public static class Reportees{
    private List<String> Rights;
    private String Authority;
    private String ID;
    private String Name;

    public List<String> getRights() {
      return Rights;
    }

    public void setRights(List<String> rights) {
      Rights = rights;
    }

    public String getAuthority() {
      return Authority;
    }

    public void setAuthority(String authority) {
      Authority = authority;
    }

    public String getID() {
      return ID;
    }

    public void setID(String ID) {
      this.ID = ID;
    }

    public String getName() {
      return Name;
    }

    public void setName(String name) {
      Name = name;
    }
  }

  public static class CredentialStatus {
    private String id;
    private String type;
    private String revocationListIndex;
    private String revocationListCredential;

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

    public String getRevocationListIndex() {
      return revocationListIndex;
    }

    public void setRevocationListIndex(String revocationListIndex) {
      this.revocationListIndex = revocationListIndex;
    }

    public String getRevocationListCredential() {
      return revocationListCredential;
    }

    public void setRevocationListCredential(String revocationListCredential) {
      this.revocationListCredential = revocationListCredential;
    }
  }

  public static class Proof {
    private String type;
    private String created;
    private String verificationMethod;
    private String proofPurpose;
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

    public String getJws() {
      return jws;
    }

    public void setJws(String jws) {
      this.jws = jws;
    }
  }
}
