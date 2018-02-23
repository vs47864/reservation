package de.fraunhofer.iosb.rest;


import de.fraunhofer.iosb.entity.User;
import de.fraunhofer.iosb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
public class UserController
{
    @Autowired
    UserService service;

    @RequestMapping("/web/users")
    public String userIndex(Model model, Model model1, Principal principal)
    {

        Iterable<User> userIterable = service.findAll();
        model.addAttribute("users", userIterable);
        model1.addAttribute("user", new User());
        return "users";
    }

    @RequestMapping("/web/users/{id:.+}")
    public String userDetails(@PathVariable("id") String id, Model model)
    {
        User user = service.findUser(id);
        model.addAttribute("user", user);
        return "userDetails";
    }

    @RequestMapping("/web/users/delete/{id:.+}")
    public String userDelete(@PathVariable("id") String id, Model model)
    {
        service.delete(id);
        return "redirect:/web/users";
    }
    @RequestMapping("/web/users/edit/{id:.+}")
    public String userEdit(@PathVariable("id") String id, Model model)
    {
        User user = service.findUser(id);
        model.addAttribute("user", user);
        return "userForm";
    }

    @RequestMapping(value = "/web/users/{id:.+}", method = RequestMethod.POST)
    public String userUpdate(@PathVariable("id") String id,User user)
    {
        if(!(user.getUsername().equals(id))){
            if(service.notexists(user.getUsername())){
                if(user.getUsername() == null)
                    service.save(user);
                else
                    service.update(user, id	);
                return "redirect:/web/users/" + user.getUsername();
            }
            else return "redirect:/web/users/error";
        }
        else{
            if(user.getUsername() == null)
                service.save(user);
            else
                service.update(user, id	);
            return "redirect:/web/users/" + user.getUsername();
        }
    }

    @RequestMapping(value= "/web/user/new", method = RequestMethod.POST)
    public String newUser(User user){
        if(service.notexists(user.getUsername())){
            service.save(user);
            return "redirect:/web/users";
        }
        else return "redirect:/web/users/error";
    }

}
