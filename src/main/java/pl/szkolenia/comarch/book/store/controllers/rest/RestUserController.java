package pl.szkolenia.comarch.book.store.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.szkolenia.comarch.book.store.database.IUserDAO;
import pl.szkolenia.comarch.book.store.model.User;

import java.util.Optional;

@RestController
public class RestUserController {

    @Autowired
    IUserDAO userDAO;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ResponseEntity<User> getUserByLogin(@RequestParam(required = false) String login) {
        if(login == null) {
            //zwracamy wszystkich userow
        } else {
            //zwracamy usera o danym loginie
        }
        Optional<User> userBox = this.userDAO.getUserByLogin(login);
        if(userBox.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(userBox.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
