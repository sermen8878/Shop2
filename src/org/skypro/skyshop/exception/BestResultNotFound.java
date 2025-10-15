package org.skypro.skyshop.exception;

public class BestResultNotFound extends Exception {
    public BestResultNotFound(String searchQuery) {
        super("Не найден подходящий результат для запроса: " + searchQuery);
    }
}