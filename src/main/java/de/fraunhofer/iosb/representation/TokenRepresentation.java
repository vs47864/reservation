package de.fraunhofer.iosb.representation;

public class TokenRepresentation
{
    private String token;

    private boolean admin;

    public TokenRepresentation() {}

    public TokenRepresentation(String token, boolean admin)
    {
        this.token = token;
        this.admin = admin;
    }

    public String getToken()
    {
        return token;
    }

    public void setToken(String token)
    {
        this.token = token;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
