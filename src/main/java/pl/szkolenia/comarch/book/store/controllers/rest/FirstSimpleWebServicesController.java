package pl.szkolenia.comarch.book.store.controllers.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.szkolenia.comarch.book.store.model.User;

@RestController
public class FirstSimpleWebServicesController {

    @RequestMapping(value = "/rest/api/user", method = RequestMethod.GET)
    public User getUser() {
        User user = new User(2, "janusz", "janusz123");

        return user;
    }


    @RequestMapping(value = "/rest/api/user/{id}", method = RequestMethod.POST)
    public User cos(
            @PathVariable int id,
            @RequestParam String param1,
            @RequestHeader String header1,
            @RequestHeader String header2,
            @RequestBody User user) {
        System.out.println(id);
        System.out.println(param1);
        System.out.println(header1);
        System.out.println(header2);
        user.setId(99);

        return user;
    }

    @RequestMapping(value = "/rest/api/user/1", method = RequestMethod.GET)
    public ResponseEntity<User> customResponse() {
        User user = new User(2, "janusz", "janusz123");

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .header("jakis naglowek 1", "value")
                .header("jakis naglowek 2", "cos")
                .body(user);
    }
}
