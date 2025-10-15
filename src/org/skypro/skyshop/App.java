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
    }
}