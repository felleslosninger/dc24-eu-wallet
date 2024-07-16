package digdir.dc24_eu_wallet.dto;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to hold data about
 * web credential befoure it will be signed.
 *
 * @author Daniel Neset
 * @version 19.07.2024
 */
public class CredentialCardDTO {
  private Payload payload;
  private String proofType;
  private String tag;
  private boolean persist;
  private boolean revocable;
  private boolean includeId;

  public Payload getPayload() {
    return payload;
  }

  public void setPayload(Payload payload) {
    this.payload = payload;
  }

  public String getProofType() {
    return proofType;
  }

  public void setProofType(String proofType) {
    this.proofType = proofType;
  }

  public String getTag() {
    return tag;
  }

  public void setTag(String tag) {
    this.tag = tag;
  }

  public boolean isPersist() {
    return persist;
  }

  public void setPersist(boolean persist) {
    this.persist = persist;
  }

  public boolean isRevocable() {
    return revocable;
  }

  public void setRevocable(boolean revocable) {
    this.revocable = revocable;
  }

  public boolean isIncludeId() {
    return includeId;
  }

  public void setIncludeId(boolean includeId) {
    this.includeId = includeId;
  }

  public static class Payload {
    private String name;
    private String description;
    private List<String> type;
    private CredentialSubject credentialSubject;
    private CredentialBranding credentialBranding;
    private Issuer issuer;
    private String expirationDate;

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

    public List<String> getType() {
      return type;
    }

    public void setType(List<String> type) {
      this.type = type;
    }

    public CredentialSubject getCredentialSubject() {
      return credentialSubject;
    }

    public void setCredentialSubject(CredentialSubject credentialSubject) {
      this.credentialSubject = credentialSubject;
    }

    public CredentialBranding getCredentialBranding() {
      return credentialBranding;
    }

    public void setCredentialBranding(CredentialBranding credentialBranding) {
      this.credentialBranding = credentialBranding;
    }

    public Issuer getIssuer() {
      return issuer;
    }

    public void setIssuer(Issuer issuer) {
      this.issuer = issuer;
    }

    public String getExpirationDate() {
      return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
      this.expirationDate = expirationDate;
    }
  }

  public static class CredentialSubject {
    private String id;
    private String sub;
    private String pid;
    private List<AuthorizationDetails> authorization_details = new ArrayList<>();

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

    public List<AuthorizationDetails> getAuthorization_details() {
      return authorization_details;
    }

    public void setAuthorization_details(List<AuthorizationDetails> authorization_details) {
      this.authorization_details = authorization_details;
    }
    public void addAuthorization_details(AuthorizationDetails authorization_details) {
      this.authorization_details.add(authorization_details);
    }
  }

  public static class AuthorizationDetails {
    private String resource;
    private String type;
    private String resource_name;
    private List<Reportees> reportees = new ArrayList<>();

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

    public List<Reportees> getReportees() {
      return reportees;
    }

    public void setReportees(List<Reportees> reportees) {
      this.reportees = reportees;
    }
    public void addReportees(Reportees reportees) {
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
    private String iconUrl;
    private String logoUrl;

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

    public String getIconUrl() {
      return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
      this.iconUrl = iconUrl;
    }

    public String getLogoUrl() {
      return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
      this.logoUrl = logoUrl;
    }
  }
}
