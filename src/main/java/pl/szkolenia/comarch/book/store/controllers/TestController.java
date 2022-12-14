package pl.szkolenia.comarch.book.store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.szkolenia.comarch.book.store.database.IOrderDAO;
import pl.szkolenia.comarch.book.store.model.Order;

import java.util.List;

@Controller
public class TestController {

    @Autowired
    IOrderDAO orderDAO;

    @RequestMapping(value = "/test/{number}/{tekst1}/{tekst2}", method = RequestMethod.GET)
    public String parametersInPath(
            @PathVariable int number,
            @PathVariable String tekst1,
            @PathVariable String tekst2
    ) {
        System.out.println(number);
        System.out.println(tekst1);
        System.out.println(tekst2);
        return "main";
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String parametersInQuery(
            @RequestParam String param1,
            @RequestParam String param2
    ) {
        System.out.println(param1);
        System.out.println(param2);
        return "main";
    }

    @RequestMapping(value = "/test/{name}/{nazwisko}", method = RequestMethod.GET)
    public String hybridParameters(
            @PathVariable String name,
            @PathVariable String nazwisko,
            @RequestParam("wiek") int age
    ) {
        System.out.println(name);
        System.out.println(nazwisko);
        System.out.println(age);
        return "main";
    }

    //@RequestMapping(value = "/test2", method = RequestMethod.GET)
    @GetMapping(value = "/test2")
    public String test2() {
        List<Order> orders = this.orderDAO.getOrders();
        System.out.println(orders);
        return "redirect:/main";
    }
}
