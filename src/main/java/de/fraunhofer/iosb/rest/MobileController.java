package de.fraunhofer.iosb.rest;


import de.fraunhofer.iosb.representation.NearbyRequest;
import de.fraunhofer.iosb.representation.RoomRepresentation;
import de.fraunhofer.iosb.representation.TokenRepresentation;
import de.fraunhofer.iosb.representation.UserCredentialRepresentation;
import de.fraunhofer.iosb.services.LoginService;
import de.fraunhofer.iosb.seucrity.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/mobile")
public class MobileController
{
    private static final Logger LOG = LoggerFactory.getLogger(MobileController.class);

    private LoginService loginService;

    @Autowired
    public MobileController(LoginService loginService)
    {
        this.loginService = loginService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public TokenRepresentation login(@Validated @RequestBody UserCredentialRepresentation userCredential)
    {
        LOG.debug("Received at /login {}", userCredential);
        boolean checked = loginService.checkUserNameAndPassword(
                userCredential.getUsername(),
                userCredential.getPassword());

        if(checked)
        {
            TokenRepresentation tokenRepresentation = new TokenRepresentation(loginService.createToken(userCredential.getUsername()));
            LOG.debug("Returns at /login {}", tokenRepresentation);
            return tokenRepresentation;
        } else
            {
            LOG.debug("Returns at /login UnauthorizedEception");
            throw new UnauthorizedException();
        }
    }

    @RequestMapping(value = "/nearby", method = RequestMethod.POST)
    public RoomRepresentation nearbyRooms(@RequestBody NearbyRequest nearbyRequest)
    {

        return null;
    }
}