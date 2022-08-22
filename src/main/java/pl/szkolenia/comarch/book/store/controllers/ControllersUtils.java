package pl.szkolenia.comarch.book.store.controllers;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import pl.szkolenia.comarch.book.store.session.SessionObject;

import javax.annotation.Resource;

@Component
public class ControllersUtils {

    @Resource
    SessionObject sessionObject;
    public void addCommonInfoToModel(Model model) {
        model.addAttribute("isLogged", this.sessionObject.isLogged());
    }
}
