package de.fraunhofer.iosb.representation;

import org.hibernate.validator.constraints.NotEmpty;

public class UserCredentialRepresentation
{
    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    public UserCredentialRepresentation() {}

    public UserCredentialRepresentation(String username, String password)
    {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString()
    {
        return "UserCredentialRepresentation{" +
                "password='" + password + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
