package pl.szkolenia.comarch.book.store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.szkolenia.comarch.book.store.database.IBookDAO;
import pl.szkolenia.comarch.book.store.model.Book;

import java.util.List;

@Controller
public class CommonController {

    @Autowired
    IBookDAO bookDAO;

    @Autowired
    ControllersUtils controllersUtils;

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main(Model model) {
        model.addAttribute("books", this.bookDAO.getBooks());
        this.controllersUtils.addCommonInfoToModel(model);
        return "main";
    }

    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public String contact(Model model) {
        this.controllersUtils.addCommonInfoToModel(model);
        return "contact";
    }
}
