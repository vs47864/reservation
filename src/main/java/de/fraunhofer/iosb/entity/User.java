package de.fraunhofer.iosb.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Entity
public class User
{
    @Override
    public String toString() {
        return username;
    }

    @Id
    public String username;

    @NotNull
    public String password;

    public String token;

    @NotNull
    public String name;

    @NotNull
    public String lastname;

    @NotNull
    public String email;

    @NotNull
    public String number;

    @ManyToOne(optional=true)
    @JoinColumn(name="RoomID",referencedColumnName="roomID")
    private Room curentRoom;

    @ManyToMany
    private List<Term> terms = new ArrayList<>();

    @ManyToMany
    private Map<String, Room> favorites = new HashMap<>();

    public User(String username, String password, String name, String lastname, String email, String number) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.number = number;
    }

    public User(String username, String password, String token, String name, String lastname,
                String email, String number) {
        this.username = username;
        this.password = password;
        this.token = token;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.number = number;
    }

    public User(){
    }

    public List<Term> getTerms() {
        return terms;
    }

    public void setTerms(List<Term> terms) {
        this.terms = terms;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Room getCurentRoom() {
        return curentRoom;
    }

    public void setCurentRoom(Room curentRoom) {
        this.curentRoom = curentRoom;
    }

    public Map<String, Room> getFavorites() {
        return favorites;
    }

    public void setFavorites(Map<String, Room> favorites) {
        this.favorites = favorites;
    }
}
