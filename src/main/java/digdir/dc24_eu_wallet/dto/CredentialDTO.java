package digdir.dc24_eu_wallet.dto;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class CredentialDTO {
  @Expose
  private List<Cred> cred;

  public List<Cred> getCred() {
    return cred;
  }

  public void setCred(List<Cred> cred) {
    this.cred = cred;
  }

  public boolean isValid() {
    return cred != null && !cred.isEmpty() && cred.stream().allMatch(Cred::isValid);
  }

  public static class Cred{
    @Expose
    private String sub;
    @Expose
    private String pid;
    @Expose
    private List<AuthorizationDetails> authorization_details;

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

    public boolean isValid() {
      return sub != null && !sub.isEmpty() &&
              pid != null && !pid.isEmpty() &&
              authorization_details != null && !authorization_details.isEmpty() &&
              authorization_details.stream().allMatch(AuthorizationDetails::isValid);
    }
  }

  public static class AuthorizationDetails {
    @Expose
    private String resource;
    @Expose
    private String type;
    @Expose
    private String resource_name;
    @Expose
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

    public boolean isValid() {
      return resource != null && !resource.isEmpty() &&
              type != null && !type.isEmpty() &&
              resource_name != null && !resource_name.isEmpty() &&
              reportees != null && !reportees.isEmpty() &&
              reportees.stream().allMatch(Reportees::isValid);
    }
  }

  public static class Reportees{
    @Expose
    private List<String> rights;
    @Expose
    private String authority;
    @Expose
    private String id;
    @Expose
    private String name;

    public List<String> getRights() {
      return rights;
    }

    public void setRights(List<String> rights) {
      this.rights = rights;
    }

    public String getAuthority() {
      return authority;
    }

    public void setAuthority(String authority) {
      this.authority = authority;
    }

    public String getID() {
      return id;
    }

    public void setID(String ID) {
      this.id = ID;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public boolean isValid() {
      return rights != null && !rights.isEmpty() &&
              authority != null && !authority.isEmpty() &&
              id != null && !id.isEmpty() &&
              name != null && !name.isEmpty();
    }
  }

}
