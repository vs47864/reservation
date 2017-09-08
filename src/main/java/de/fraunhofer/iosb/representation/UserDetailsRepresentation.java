package de.fraunhofer.iosb.representation;

import java.util.ArrayList;
import java.util.List;

public class UserDetailsRepresentation extends  UserRepresentation
{
    String email;

    String phoneNumber;

    List<TermsResponse> terms = new ArrayList<>();

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<TermsResponse> getTerms() {
        return terms;
    }

    public void setTerms(List<TermsResponse> terms) {
        this.terms = terms;
    }
}
