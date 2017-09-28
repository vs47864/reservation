package de.fraunhofer.iosb.rest;


import de.fraunhofer.iosb.representation.UserCredentialRepresentation;
import de.fraunhofer.iosb.services.LoginService;
import de.fraunhofer.iosb.seucrity.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Viseslav Sako
 */
@Controller
public class AdminController {
    @Autowired
    LoginService service;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String begin() {
        return "index";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String login(@Validated UserCredentialRepresentation userCredential)
    {
        boolean checked = service.checkUserNameAndPassword(
                userCredential.getUsername(),
                userCredential.getPassword());

        if (checked) {
            return "loginsucces";
        } else {
            throw new UnauthorizedException();
        }
    }

    @RequestMapping(value = "/web", method = RequestMethod.GET)
    public String Index() {
        return "index";
    }
}