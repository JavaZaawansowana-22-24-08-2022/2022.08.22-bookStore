package pl.szkolenia.comarch.book.store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.annotation.SessionScope;
import pl.szkolenia.comarch.book.store.database.IUserDAO;
import pl.szkolenia.comarch.book.store.model.User;
import pl.szkolenia.comarch.book.store.session.SessionObject;

import javax.annotation.Resource;
import java.util.Optional;

@Controller
public class AuthenticationController {

    @Autowired
    IUserDAO userDAO;

    @Resource
    SessionObject sessionObject;

    @Autowired
    ControllersUtils  controllersUtils;

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
        Optional<User> userBox = this.userDAO.getUserByLogin(user.getLogin());
        if(userBox.isPresent() && userBox.get().getPassword().equals(user.getPassword())) {
            this.sessionObject.setLogged(true);
            return "redirect:/main";
        }

        return "redirect:/login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() {
        this.sessionObject.setLogged(false);
        return "redirect:/login";
    }
}
