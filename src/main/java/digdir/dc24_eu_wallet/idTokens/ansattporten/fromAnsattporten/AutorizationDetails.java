package digdir.dc24_eu_wallet.idTokens.ansattporten.fromAnsattporten;

import java.util.ArrayList;
import java.util.List;

/**
 * Holds information from the authorization_details field in the payload
 * of oidc id token.
 *
 * @author Langbakk
 * @version 16.07.2024
 */
public class AutorizationDetails {

    private String resource;
    private String type;
    private String resource_name;
    private List<Reportee> reportees;
    private String Authority;
    private String ID;
    private String Name;


    public AutorizationDetails(){
    }

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

    public List<Reportee> getReportees() {
        return reportees;
    }

    public void setReportees(List<Reportee> reportees) {
        this.reportees = reportees;
    }

    public String getAuthority() {
        return Authority;
    }

    public void setAuthority(String authority) {
        this.Authority = authority;
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
        this.Name = name;
    }

    public void mattrSetReportee(Reportee reportee){
        this.reportees = new ArrayList<>();
        //ArrayList <Reportee>reportees = new ArrayList<>();
        reportees.add(reportee);
    }

}