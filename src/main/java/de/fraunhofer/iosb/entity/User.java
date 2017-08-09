package de.fraunhofer.iosb.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


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

    public String cardId;

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

    public User(String username, String password, String name, String lastname, String email, String number) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.number = number;
    }

    public User(String username, String password, String token, String cardId, String name, String lastname,
                String email, String number) {
        this.username = username;
        this.password = password;
        this.token = token;
        this.cardId = cardId;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.number = number;
    }

    public User(){
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

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
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
}
