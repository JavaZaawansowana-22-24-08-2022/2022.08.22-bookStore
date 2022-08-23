package pl.szkolenia.comarch.book.store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.szkolenia.comarch.book.store.model.User;
import pl.szkolenia.comarch.book.store.services.IAuthenticationService;
import pl.szkolenia.comarch.book.store.session.SessionObject;

import javax.annotation.Resource;

@Controller
public class AuthenticationController {

    @Resource
    SessionObject sessionObject;

    @Autowired
    ControllersUtils  controllersUtils;

    @Autowired
    IAuthenticationService authenticationService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginForm(Model model) {
        model.addAttribute("userObject", new User());
        this.controllersUtils.addCommonInfoToModel(model);
        return "login";
    }

    /*@RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam String login, @RequestParam String password) {
        System.out.println(login);
        System.out.println(password);
        return "main";
    }*/

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@ModelAttribute User user) {
        this.authenticationService.authenticate(user.getLogin(), user.getPassword());
        if(this.sessionObject.isLogged()) {
            return "redirect:/main";
        }

        return "redirect:/login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() {
        this.authenticationService.logout();
        return "redirect:/login";
    }
}
