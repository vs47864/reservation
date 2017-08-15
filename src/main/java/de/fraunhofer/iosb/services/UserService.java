package de.fraunhofer.iosb.services;

import de.fraunhofer.iosb.representation.UserRepresentation;

import java.util.List;

public interface UserService
{
    List<UserRepresentation> getAllUsersInRepresentation();
}
