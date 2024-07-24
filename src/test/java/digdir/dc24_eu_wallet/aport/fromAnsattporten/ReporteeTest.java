package digdir.dc24_eu_wallet.aport.fromAnsattporten;

import digdir.dc24_eu_wallet.idTokens.ansattporten.fromAnsattporten.Reportee;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ReporteeTest {

    private Reportee reportee;

    @BeforeEach
    void setUp() {
        reportee = new Reportee();
    }

    @AfterEach
    void tearDown() {
        reportee = null;
    }

    @Test
    void setRights() {
        ArrayList<String> rights = new ArrayList<>();
        rights.add("READ");
        rights.add("WRITE");
        reportee.setRights(rights);
        assertEquals(rights, reportee.getRights());
    }

    @Test
    void getRights() {
        ArrayList<String> rights = new ArrayList<>();
        rights.add("READ");
        reportee.setRights(rights);
        assertEquals(rights, reportee.getRights());
    }

    @Test
    void getAuthority() {
        reportee.setAuthority("testAuthority");
        assertEquals("testAuthority", reportee.getAuthority());
    }

    @Test
    void setAuthority() {
        assertNull(reportee.getAuthority());
        reportee.setAuthority("newAuthority");
        assertEquals("newAuthority", reportee.getAuthority());
    }

    @Test
    void getID() {
        reportee.setID("testID");
        assertEquals("testID", reportee.getID());
    }

    @Test
    void setID() {
        assertNull(reportee.getID());
        reportee.setID("newID");
        assertEquals("newID", reportee.getID());
    }

    @Test
    void getName() {
        reportee.setName("testName");
        assertEquals("testName", reportee.getName());
    }

    @Test
    void setName() {
        assertNull(reportee.getName());
        reportee.setName("newName");
        assertEquals("newName", reportee.getName());
    }
}