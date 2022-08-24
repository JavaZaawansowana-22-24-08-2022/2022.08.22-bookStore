package pl.szkolenia.comarch.book.store.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.szkolenia.comarch.book.store.database.IOrderDAO;
import pl.szkolenia.comarch.book.store.model.Order;
import pl.szkolenia.comarch.book.store.model.dto.OrderListResponse;

@RestController
@RequestMapping(value = "/api/v1/order")
public class RestOrderController {

    @Autowired
    IOrderDAO orderDAO;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity saveOrder(@RequestBody Order order) {
        order.setId(0);
        order.getOrderPositions().stream().forEach(orderPosition -> orderPosition.setId(0));
        this.orderDAO.persistOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public OrderListResponse getOrders() {
        OrderListResponse response = new OrderListResponse();
        response.setOrders(this.orderDAO.getOrders());
        return response;
    }
}
