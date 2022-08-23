package pl.szkolenia.comarch.book.store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.szkolenia.comarch.book.store.services.ICartService;

@Controller
public class CartController {

    @Autowired
    ControllersUtils controllersUtils;

    @Autowired
    ICartService cartService;

    @RequestMapping(value = "/cart/add/{bookId}", method = RequestMethod.GET)
    public String addToCart(@PathVariable int bookId) {
        this.cartService.addToCart(bookId);
        return "redirect:/main";
    }

    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    public String cart(Model model) {
        model.addAttribute("cartPositions", this.cartService.getViewModelCart());
        this.controllersUtils.addCommonInfoToModel(model);
        return "cart";
    }

    @RequestMapping(value = "/cart/confirm", method = RequestMethod.GET)
    public String confirm() {
        this.cartService.confirmOrder();
        return "redirect:/main";
    }
}
