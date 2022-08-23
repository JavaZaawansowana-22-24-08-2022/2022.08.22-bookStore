package pl.szkolenia.comarch.book.store.model;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<Integer, Integer> cartPositions = new HashMap<>();

    public Map<Integer, Integer> getCartPositions() {
        return cartPositions;
    }
}
