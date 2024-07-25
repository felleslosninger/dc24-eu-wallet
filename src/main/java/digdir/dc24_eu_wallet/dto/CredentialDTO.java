package digdir.dc24_eu_wallet.dto;

import com.google.gson.annotations.Expose;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Information parsed from ansattporten
 * used to create a subject used in CredentialCardDTO.
 *
 * @author Daniel Neset
 * @version 16.07.2024
 */
@Setter
@Getter
public class CredentialDTO {
  @Expose
  private List<Cred> cred = new ArrayList<>();

  public void addCred(Cred cred) {
    this.cred.add(cred);
  }

  public boolean isValid() {
    return cred != null && !cred.isEmpty() && cred.stream().allMatch(Cred::isValid);
  }

  /**
   * Represents a credential with subject, personal identifier, and authorization details.
   */
  @Setter
  @Getter
  public static class Cred {
    @Expose
    private String sub;
    @Expose
    private String pid;
    @Expose
    private List<AuthorizationDetails> authorizationDetails = new ArrayList<>();

      public void addAuthorizationDetails(AuthorizationDetails authorizationDetail) {
        this.authorizationDetails.add(authorizationDetail);
    }

    public boolean isValid() {
        return sub != null && !sub.isEmpty() && pid != null && !pid.isEmpty() &&
              authorizationDetails != null && !authorizationDetails.isEmpty() &&
              authorizationDetails.stream().allMatch(AuthorizationDetails::isValid);
    }
  }

  /**
   * Represents the authorization details of a credential.
   */
  @Setter
  @Getter
  public static class AuthorizationDetails {
    @Expose
    private String resource;
    @Expose
    private String type;
    @Expose
    private String resourceName;
    @Expose
    private List<Reportees> reportees = new ArrayList<>();

    public void addReportees(Reportees reportees) {
      this.reportees.add(reportees);
    }

    public boolean isValid() {
      return resource != null && !resource.isEmpty() &&
              type != null && !type.isEmpty() &&
              resourceName != null && !resourceName.isEmpty() &&
              reportees != null && !reportees.isEmpty() &&
              reportees.stream().allMatch(Reportees::isValid);
    }
  }

  /**
   * Represents the reportees associated with the authorization.
   */
  public static class Reportees {
    @Setter
    @Getter
    @Expose
    private List<String> rights;
    @Setter
    @Getter
    @Expose
    private String authority;
    @Expose
    private String id;
    @Setter
    @Getter
    @Expose
    private String name;

      public String getID() {
      return id;
    }

    public void setID(String id) {
      this.id = id;
    }

      public boolean isValid() {
      return rights != null && !rights.isEmpty() &&
              authority != null && !authority.isEmpty() &&
              id != null && !id.isEmpty() &&
              name != null && !name.isEmpty();
    }
  }
}