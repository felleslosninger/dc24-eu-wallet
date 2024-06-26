enum ConstsIssuer {
    ROOT = "/",
    VERSION = "1",
    HEALTH = "health",
    ISSUER = "issuer"

    private final String value;

    ConstsIssuer(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}


