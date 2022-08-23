package pl.szkolenia.comarch.book.store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.szkolenia.comarch.book.store.services.IBookService;

@Controller
public class CommonController {

    @Autowired
    IBookService bookService;

    @Autowired
    ControllersUtils controllersUtils;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String main() {
        return "redirect:/main";
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main(Model model) {
        model.addAttribute("books", this.bookService.getAllBooks());
        this.controllersUtils.addCommonInfoToModel(model);
        return "main";
    }

    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public String contact(Model model) {
        this.controllersUtils.addCommonInfoToModel(model);
        return "contact";
    }
}
