package digdir.dc24_eu_wallet.aport.toMattr;

import com.google.gson.annotations.Expose;
import org.springframework.web.service.annotation.PostExchange;

import java.util.ArrayList;
import java.util.List;

/**
 * Credential class that is used to format data so it can
 * be used in the Mattr API.
 *
 * @author Daniel Neset
 * @version 17.07.2024
 */
public class Credential {
  @Expose
  private List<Cred> cred = new ArrayList<>();

  public List<Cred> getCred() {
    return cred;
  }

  public void setCred(List<Cred> cred) {
    this.cred = cred;
  }

  public void addCred(Cred cred) {
    this.cred.add(cred);
  }

  public static class Cred{
    @Expose
    private String sub;
    @Expose
    private String pid;
    @Expose
    private List<Authorization_details> authorization_details = new ArrayList<>();

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

    public List<Authorization_details> getAuthorization_details() {
      return authorization_details;
    }

    public void setAuthorization_details(List<Authorization_details> authorization_details) {
      this.authorization_details = authorization_details;
    }

    public void addAuthorization_details(Authorization_details authorizationDetails){
      this.authorization_details.add(authorizationDetails);
    }
  }

  public static class Authorization_details{

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
  }

}


