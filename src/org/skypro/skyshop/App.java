import org.skypro.skyshop.exception.BestResultNotFound;
package org.skypro.skyshop;

import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.Product;

public class App {
    public static void main(String[] args) {
        // Создание продуктов
        Product milk = new Product("Молоко", 80);
        Product bread = new Product("Хлеб", 40);
        Product cheese = new Product("Сыр", 200);
        Product juice = new Product("Сок", 100);
        Product yogurt = new Product("Йогурт", 50);
        Product butter = new Product("Масло", 150); // Этот не добавится из-за лимита

        // Создание корзины
        ProductBasket basket = new ProductBasket();

        // 1. Добавление продукта в корзину.
        basket.addProduct(milk);
        basket.addProduct(bread);
        basket.addProduct(cheese);
        basket.addProduct(juice);
        basket.addProduct(yogurt);

        // 2. Добавление продукта в заполненную корзину.
        basket.addProduct(butter); // Должно появиться сообщение "Невозможно добавить продукт"

        // 3. Печать содержимого корзины с несколькими товарами.
        System.out.println("--- Корзина с товарами ---");
        basket.printBasket();

        // 4. Получение стоимости корзины с несколькими товарами.
        System.out.println("--- Общая стоимость ---");
        System.out.println(basket.getTotalPrice());

        // 5. Поиск товара, который есть в корзине.
        System.out.println("--- Поиск 'Хлеб' (есть) ---");
        System.out.println(basket.containsProduct("Хлеб"));

        // 6. Поиск товара, которого нет в корзине.
        System.out.println("--- Поиск 'Колбаса' (нет) ---");
        System.out.println(basket.containsProduct("Колбаса"));

        // 7. Очистка корзины.
        basket.clearBasket();

        // 8. Печать содержимого пустой корзины.
        System.out.println("--- Пустая корзина ---");
        basket.printBasket();

        // 9. Получение стоимости пустой корзины.
        System.out.println("--- Стоимость пустой корзины ---");
        System.out.println(basket.getTotalPrice());

        // 10. Поиск товара по имени в пустой корзине.
        System.out.println("--- Поиск в пустой корзине ---");
        System.out.println(basket.containsProduct("Молоко"));
        // Демонстрация исключений
        System.out.println("\n=== ДЕМОНСТРАЦИЯ ИСКЛЮЧЕНИЙ ===");

// Демонстрация валидации в конструкторах
        try {
            System.out.println("1. Попытка создать продукт с пустым именем:");
            SimpleProduct invalidProduct = new SimpleProduct("", 100);
        } catch (IllegalArgumentException e) {
            System.out.println("Поймано исключение: " + e.getMessage());
        }

        try {
            System.out.println("2. Попытка создать продукт с отрицательной ценой:");
            SimpleProduct invalidProduct = new SimpleProduct("Телевизор", -100);
        } catch (IllegalArgumentException e) {
            System.out.println("Поймано исключение: " + e.getMessage());
        }

        try {
            System.out.println("3. Попытка создать продукт с невалидной скидкой:");
            DiscountedProduct invalidProduct = new DiscountedProduct("Наушники", 5000, 150);
        } catch (IllegalArgumentException e) {
            System.out.println("Поймано исключение: " + e.getMessage());
        }

// Демонстрация поиска лучшего совпадения
        try {
            System.out.println("4. Поиск лучшего совпадения для существующего запроса:");
            SearchEngine searchEngine = new SearchEngine(10);
            // Добавляем тестовые данные
            searchEngine.add(milk);
            searchEngine.add(bread);
            searchEngine.add(laptop);
            searchEngine.add(phone);

            Searchable bestMatch = searchEngine.findBestMatch("Молоко");
            System.out.println("Найден лучший результат: " + bestMatch.getName());
        } catch (BestResultNotFound e) {
            System.out.println("Поймано исключение: " + e.getMessage());
        }

        try {
            System.out.println("5. Поиск лучшего совпадения для несуществующего запроса:");
            SearchEngine emptyEngine = new SearchEngine(10);
            Searchable bestMatch = emptyEngine.findBestMatch("НесуществующийТовар");
        } catch (BestResultNotFound e) {
            System.out.println("Поймано исключение: " + e.getMessage());
        }
    }
}