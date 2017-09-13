package de.fraunhofer.iosb.entity;



import de.fraunhofer.iosb.entity.key.RoleId;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class Role {
	@EmbeddedId
	RoleId id;

	private String role;
	
	@ManyToOne(optional=false)
	@MapsId("roomId")
	private Room room;

	@ManyToOne(optional=false)
    @MapsId("userName")
	private User user;
	
	
	public Role() {
		id = new RoleId();
	}

	public RoleId getId() {
		return id;
	}

	public void setId(RoleId id) {
		this.id = id;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
}
