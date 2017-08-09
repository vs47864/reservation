package de.fraunhofer.iosb.representation;

public class TokenRepresentation
{
    private String token;
    public TokenRepresentation() {}

    public TokenRepresentation(String token)
    {
        this.token = token;
    }

    public String getToken()
    {
        return token;
    }

    public void setToken(String token)
    {
        this.token = token;
    }

    @Override
    public String toString()
    {
        return "TokenRepresentation{" +
                "token='" + token + '\'' +
                '}';
    }
}
