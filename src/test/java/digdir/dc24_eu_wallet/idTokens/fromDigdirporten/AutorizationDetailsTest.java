package digdir.dc24_eu_wallet.idTokens.fromDigdirporten;

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
    void mattrSetReportee() {
        Reportee reportee = new Reportee();
        autorizationDetails.mattrSetReportee(reportee);

        List<Reportee> reportees = autorizationDetails.getReportees();

        assertEquals(1, reportees.size());
        assertTrue(reportees.contains(reportee));
    }

    @Test
    void getResource() {
        autorizationDetails.setResource("testResource");
        assertEquals("testResource", autorizationDetails.getResource());
    }

    @Test
    void getType() {
        autorizationDetails.setType("testType");
        assertEquals("testType", autorizationDetails.getType());
    }

    @Test
    void getResource_name() {
        autorizationDetails.setResource_name("testResource_name");
        assertEquals("testResource_name", autorizationDetails.getResource_name());
    }

    @Test
    void getReportees() {
        autorizationDetails.setReportees(new ArrayList<>());
        assertEquals(0, autorizationDetails.getReportees().size());
    }

    @Test
    void getAuthority() {
        autorizationDetails.setAuthority("testAuthority");
        assertEquals("testAuthority", autorizationDetails.getAuthority());
    }

    @Test
    void getId() {
        autorizationDetails.setId("testId");
        assertEquals("testId", autorizationDetails.getId());
    }

    @Test
    void getName() {
        autorizationDetails.setName("testName");
        assertEquals("testName", autorizationDetails.getName());
    }

    @Test
    void setResource() {
        assertNull(autorizationDetails.getResource());
        autorizationDetails.setResource("newResource");
        assertEquals("newResource", autorizationDetails.getResource());
    }

    @Test
    void setType() {
        assertNull(autorizationDetails.getType());
        autorizationDetails.setType("newType");
        assertEquals("newType", autorizationDetails.getType());
    }

    @Test
    void setResource_name() {
        assertNull(autorizationDetails.getResource_name());
        autorizationDetails.setResource_name("newResource_name");
        assertEquals("newResource_name", autorizationDetails.getResource_name());
    }

    @Test
    void setReportees() {
        assertNull(autorizationDetails.getReportees());
        autorizationDetails.setReportees(new ArrayList<>());
        assertEquals(0, autorizationDetails.getReportees().size());
    }

    @Test
    void setAuthority() {
        assertNull(autorizationDetails.getAuthority());
        autorizationDetails.setAuthority("newAuthority");
        assertEquals("newAuthority", autorizationDetails.getAuthority());
    }

    @Test
    void setId() {
        assertNull(autorizationDetails.getId());
        autorizationDetails.setId("newId");
        assertEquals("newId", autorizationDetails.getId());
    }

    @Test
    void setName() {
        assertNull(autorizationDetails.getName());
        autorizationDetails.setName("newName");
        assertEquals("newName", autorizationDetails.getName());
    }
}