package digdir.dc24_eu_wallet.dto;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ChallengerAndCred {

  private String challenger;
  private List<Cred> cred;

  public String getChallenger() {
    return challenger;
  }

  public void setChallenger(String challenger) {
    this.challenger = challenger;
  }

  public List<Cred> getCred() {
    return cred;
  }

  public void setCred(List<Cred> cred) {
    this.cred = cred;
  }

  public static class Cred{
    private String sub;
    private String pid;
    private List<CredentialPost.AuthorizationDetails> authorization_details;

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

    public List<CredentialPost.AuthorizationDetails> getAuthorization_details() {
      return authorization_details;
    }

    public void setAuthorization_details(List<CredentialPost.AuthorizationDetails> authorization_details) {
      this.authorization_details = authorization_details;
    }
  }

  public static class AuthorizationDetails {
    private String resource;
    private String type;
    private String resource_name;
    private List<CredentialPost.Reportees> reportees = new ArrayList<>();

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

    public List<CredentialPost.Reportees> getReportees() {
      return reportees;
    }

    public void setReportees(List<CredentialPost.Reportees> reportees) {
      this.reportees = reportees;
    }
    public void addReportees(CredentialPost.Reportees reportees) {
      this.reportees.add(reportees);
    }
  }

  public static class Reportees{
    @SerializedName("Rights")
    private List<String> Rights;
    @SerializedName("Authority")
    private String Authority;
    @SerializedName("ID")
    private String ID;
    @SerializedName("Name")
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

}
