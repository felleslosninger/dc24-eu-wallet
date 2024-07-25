package digdir.dc24_eu_wallet.aport.fromAnsattporten;

import digdir.dc24_eu_wallet.idTokens.ansattporten.fromAnsattporten.AutorizationDetails;
import digdir.dc24_eu_wallet.idTokens.ansattporten.fromAnsattporten.Reportee;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AutorizationDetailsTest {

    private AutorizationDetails autorizationDetails;

    @BeforeEach
    void setUp() {
        autorizationDetails = new AutorizationDetails();
    }

    @AfterEach
    void tearDown() {
        autorizationDetails = null;
    }

    @Test
    void getResource() {
        autorizationDetails.setResource("testResource");
        assertEquals("testResource", autorizationDetails.getResource());
    }

    @Test
    void setResource() {
        assertNull(autorizationDetails.getResource());
        autorizationDetails.setResource("newResource");
        assertEquals("newResource", autorizationDetails.getResource());
    }

    @Test
    void getType() {
        autorizationDetails.setType("testType");
        assertEquals("testType", autorizationDetails.getType());
    }

    @Test
    void setType() {
        assertNull(autorizationDetails.getType());
        autorizationDetails.setType("newType");
        assertEquals("newType", autorizationDetails.getType());
    }

    @Test
    void getResource_name() {
        autorizationDetails.setResource_name("testResourceName");
        assertEquals("testResourceName", autorizationDetails.getResource_name());
    }

    @Test
    void setResource_name() {
        assertNull(autorizationDetails.getResource_name());
        autorizationDetails.setResource_name("newResourceName");
        assertEquals("newResourceName", autorizationDetails.getResource_name());
    }

    @Test
    void getReportees() {
        List<Reportee> reportees = new ArrayList<>();
        autorizationDetails.setReportees(reportees);
        assertEquals(reportees, autorizationDetails.getReportees());
    }

    @Test
    void setReportees() {
        List<Reportee> initialReportees = new ArrayList<>();
        initialReportees.add(new Reportee());
        autorizationDetails.setReportees(initialReportees);

        List<Reportee> newReportees = new ArrayList<>();
        newReportees.add(new Reportee());
        newReportees.add(new Reportee());

        autorizationDetails.setReportees(newReportees);
        assertEquals(newReportees, autorizationDetails.getReportees());
        assertEquals(2, autorizationDetails.getReportees().size());
    }

    @Test
    void getAuthority() {
        autorizationDetails.setAuthority("testAuthority");
        assertEquals("testAuthority", autorizationDetails.getAuthority());
    }

    @Test
    void setAuthority() {
        assertNull(autorizationDetails.getAuthority());
        autorizationDetails.setAuthority("newAuthority");
        assertEquals("newAuthority", autorizationDetails.getAuthority());
    }

    @Test
    void getID() {
        autorizationDetails.setId("testID");
        assertEquals("testID", autorizationDetails.getId());
    }

    @Test
    void setID() {
        assertNull(autorizationDetails.getId());
        autorizationDetails.setId("newID");
        assertEquals("newID", autorizationDetails.getId());
    }

    @Test
    void getName() {
        autorizationDetails.setName("testName");
        assertEquals("testName", autorizationDetails.getName());
    }

    @Test
    void setName() {
        assertNull(autorizationDetails.getName());
        autorizationDetails.setName("newName");
        assertEquals("newName", autorizationDetails.getName());
    }

    @Test
    void mattrSetReportee() {
        Reportee reportee = new Reportee();
        autorizationDetails.mattrSetReportee(reportee);
        assertTrue(autorizationDetails.getReportees().contains(reportee));
    }
}