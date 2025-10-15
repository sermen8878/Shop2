package org.skypro.skyshop;

import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.*;
import org.skypro.skyshop.search.SearchEngine;
import org.skypro.skyshop.search.Searchable;
import org.skypro.skyshop.search.Article;

import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        System.out.println("=== Демонстрация работы интернет-магазина ===");

        // Создаем продукты разных типов
        SimpleProduct phone = new SimpleProduct("Телефон", 50000);
        SimpleProduct laptop = new SimpleProduct("Ноутбук", 100000);
        DiscountedProduct headphones = new DiscountedProduct("Наушники", 15000, 20);
        FixPriceProduct mouse = new FixPriceProduct("Мышь");
        FixPriceProduct keyboard = new FixPriceProduct("Клавиатура");

        // Создаем корзину
        ProductBasket basket = new ProductBasket();

        System.out.println("\n=== 1. Добавление продуктов в корзину ===");
        basket.addProduct(phone);
        basket.addProduct(laptop);
        basket.addProduct(headphones);
        basket.addProduct(mouse);
        basket.addProduct(keyboard);

        // Пытаемся добавить еще один продукт (теперь без ограничения размера)
        basket.addProduct(new SimpleProduct("Планшет", 30000));
        System.out.println("Добавлен планшет - ограничения по размеру больше нет!");

        System.out.println("\n=== 2. Печать содержимого корзины ===");
        basket.printBasket();

        System.out.println("\n=== 3. Получение общей стоимости корзины ===");
        System.out.println("Общая стоимость: " + basket.getTotalPrice());

        System.out.println("\n=== 4. Поиск товаров в корзине ===");
        System.out.println("Есть ли 'Телефон' в корзине: " + basket.containsProduct("Телефон"));
        System.out.println("Есть ли 'Монитор' в корзине: " + basket.containsProduct("Монитор"));

        System.out.println("\n=== 5. Демонстрация удаления по имени ===");

        // Удаляем существующий продукт
        List<Product> removedProducts = basket.removeProductsByName("Телефон");
        System.out.println("Удаленные продукты 'Телефон':");
        if (removedProducts.isEmpty()) {
            System.out.println("Список пуст");
        } else {
            removedProducts.forEach(p -> System.out.println(" - " + p));
        }

        System.out.println("\nКорзина после удаления телефона:");
        basket.printBasket();

        // Пытаемся удалить несуществующий продукт
        List<Product> notRemoved = basket.removeProductsByName("Несуществующий");
        System.out.println("\nПопытка удалить несуществующий продукт:");
        if (notRemoved.isEmpty()) {
            System.out.println("Список пуст - продукт не найден");
        }

        System.out.println("\n=== 6. Очистка корзины ===");
        basket.clear();

        System.out.println("=== 7. Печать пустой корзины ===");
        basket.printBasket();

        System.out.println("=== 8. Получение стоимости пустой корзины ===");
        System.out.println("Стоимость пустой корзины: " + basket.getTotalPrice());

        System.out.println("=== 9. Поиск в пустой корзине ===");
        System.out.println("Есть ли 'Телефон' в пустой корзине: " + basket.containsProduct("Телефон"));

        // Восстанавливаем продукты для демонстрации поиска
        basket.addProduct(phone);
        basket.addProduct(laptop);
        basket.addProduct(headphones);

        System.out.println("\n=== 10. Демонстрация работы поискового движка ===");

        // Создаем поисковый движок
        SearchEngine engine = new SearchEngine();

        // Добавляем все продукты в поисковый движок
        engine.add(phone);
        engine.add(laptop);
        engine.add(headphones);
        engine.add(mouse);
        engine.add(keyboard);

        // Создаем несколько статей для поиска
        Article article1 = new Article("Обзор телефона", "Новый телефон обладает отличными характеристиками...");
        Article article2 = new Article("Игровой ноутбук", "Мощный ноутбук для игр и работы...");

        engine.add(article1);
        engine.add(article2);

        // Демонстрируем поиск
        System.out.println("\nРезультаты поиска 'тел':");
        Map<String, Searchable> results1 = engine.search("тел");
        results1.forEach((name, item) ->
                System.out.println(" - " + name + ": " + item.getStringRepresentation())
        );

        System.out.println("\nРезультаты поиска 'ноут':");
        Map<String, Searchable> results2 = engine.search("ноут");
        results2.forEach((name, item) ->
                System.out.println(" - " + name + ": " + item.getStringRepresentation())
        );

        System.out.println("\nРезультаты поиска 'игр':");
        Map<String, Searchable> results3 = engine.search("игр");
        results3.forEach((name, item) ->
                System.out.println(" - " + name + ": " + item.getStringRepresentation())
        );

        System.out.println("\n=== Демонстрация завершена ===");
    }
}
