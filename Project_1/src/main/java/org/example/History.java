package org.example;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class History {
    private final HashMap<LocalDateTime, Order> history;

    public History() {
        history = new HashMap<>();
    }

    public Order addOrder(Order order) {
        return history.put(LocalDateTime.now(), order);
    }

    public void printHistory() {
        for (Map.Entry<LocalDateTime, Order> entry: history.entrySet()) {
            System.out.println(entry);
        }
    }
}
