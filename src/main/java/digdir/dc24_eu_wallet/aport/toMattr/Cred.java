package digdir.dc24_eu_wallet.aport.toMattr;

import digdir.dc24_eu_wallet.aport.fromAnsattporten.AutorizationDetails;
import digdir.dc24_eu_wallet.aport.fromAnsattporten.Reportee;
import digdir.dc24_eu_wallet.aport.fromAnsattporten.TokenHead;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * Its purpose is to hold the contents of the "cred" field in the json string with information for MATTR.
 * It is part of the payload of the request we send to MATTR to issue something.
 *
 */

public class Cred {
    @Getter
    private String sub;
    @Getter
    private String pid;
    private List<AutorizationDetails> authorization_details;
    private TokenHead token;
    private Reportee reportee;

    /**
     * Constructs Cred as class, and will hold information for that field in the JSON string. Initializes token,
     * authentication_details, pid and sub.
     *
     * @param token token is the topmost object of the payload of the decoded OIDC id token of the user which is
     *              logged in.
     */

    public Cred(TokenHead token, Reportee reportee) {
        this.reportee = reportee;
        this.token = token;
        setAuthorization_details(reportee);
        setPid();
        setSub();
    }

    /**
     * The TokenHead class contains a method which returns the authorization_details as a list of objects of type
     * AutorizationDetails. This is what we set the autorization details to, so that the assigning is dynamic.
     *
     */

    public void setAuthorization_details(Reportee reportee) {

        List<AutorizationDetails> temp_authorization_details = token.getAuthorizationDetails();
        System.out.println(temp_authorization_details.size());
        ArrayList<Reportee> tempList = new ArrayList<>();
        tempList.add(reportee);
        System.out.println("Size of temp list : "+tempList.size() + " plus content: "+ tempList.get(0).getName());
        temp_authorization_details.get(0).setReportees(tempList);
        //Todo: jeg bare flytta ut this. fra forran
        authorization_details = temp_authorization_details;
        //this.authorization_details.get(0).mattrSetReportee(reportee);

        System.out.println("This is it after saying this.aut_deets  autdettes   "+ authorization_details.get(0).getReportees().get(0).getName());


        /**

        ArrayList<AutorizationDetails> details = new ArrayList<>();
        for (Reportee reportee : token.getAuthorizationDetails().get(0).getReportees()){
            AutorizationDetails thisSpecificAutDetail = token.getAuthorizationDetails().get(0);
            thisSpecificAutDetail.mattrSetReportee(reportee);
            details.add(thisSpecificAutDetail);
        }
        this.authorization_details = details;
         */
    }

    public List<AutorizationDetails> getAuthorization_details() {
        return authorization_details;
    }

    public void setPid() {
        pid = token.getPid();
    }

    public void setSub(){
        sub = token.getSub();
    }
}