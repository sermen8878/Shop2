package org.skypro.skyshop;

import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.SimpleProduct;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.search.SearchEngine;
import org.skypro.skyshop.search.Searchable;
import org.skypro.skyshop.exception.BestResultNotFound;
import java.util.List;

public class App {
        public static void main(String[] args) {
                // Создание продуктов разных типов
                SimpleProduct milk = new SimpleProduct("Молоко", 80);
                SimpleProduct bread = new SimpleProduct("Хлеб", 40);
                SimpleProduct cheese = new SimpleProduct("Сыр", 200);

                DiscountedProduct laptop = new DiscountedProduct("Ноутбук", 50000, 10);
                DiscountedProduct phone = new DiscountedProduct("Телефон", 30000, 15);

                FixPriceProduct chocolate = new FixPriceProduct("Шоколад");
                FixPriceProduct coffee = new FixPriceProduct("Кофе");

                // Создание корзины
                ProductBasket basket = new ProductBasket();

                // 1. Добавление продуктов в корзину
                basket.addProduct(milk);
                basket.addProduct(bread);
                basket.addProduct(cheese);
                basket.addProduct(laptop);
                basket.addProduct(phone);
                basket.addProduct(chocolate);
                basket.addProduct(coffee);

                // 2. Печать содержимого корзины с несколькими товарами
                System.out.println("--- Корзина с товарами ---");
                basket.printBasket();

                // 3. Получение стоимости корзины с несколькими товарами
                System.out.println("--- Общая стоимость ---");
                System.out.println(basket.getTotalPrice());

                // 4. Поиск товара, который есть в корзине
                System.out.println("--- Поиск 'Хлеб' (есть) ---");
                System.out.println(basket.containsProduct("Хлеб"));

                // 5. Поиск товара, которого нет в корзине
                System.out.println("--- Поиск 'Колбаса' (нет) ---");
                System.out.println(basket.containsProduct("Колбаса"));

                // 6. Очистка корзины
                basket.clearBasket();

                // 7. Печать содержимого пустой корзины
                System.out.println("--- Пустая корзина ---");
                basket.printBasket();

                // 8. Получение стоимости пустой корзины
                System.out.println("--- Стоимость пустой корзины ---");
                System.out.println(basket.getTotalPrice());

                // 9. Поиск товара по имени в пустой корзине
                System.out.println("--- Поиск в пустой корзине ---");
                System.out.println(basket.containsProduct("Молоко"));

                // Демонстрация исключений
                System.out.println("\n=== ДЕМОНСТРАЦИЯ ИСКЛЮЧЕНИЙ ===");

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

                // Демонстрация работы с List и нового метода удаления
                System.out.println("\n=== ДЕМОНСТРАЦИЯ LIST И УДАЛЕНИЯ ===");

                // Создаем новую корзину для демонстрации
                ProductBasket demoBasket = new ProductBasket();

                // Добавляем несколько товаров, в том числе дубликаты
                demoBasket.addProduct(new SimpleProduct("Яблоко", 50));
                demoBasket.addProduct(new SimpleProduct("Банан", 30));
                demoBasket.addProduct(new SimpleProduct("Яблоко", 50)); // дубликат
                demoBasket.addProduct(new SimpleProduct("Апельсин", 70));
                demoBasket.addProduct(new DiscountedProduct("Яблоко", 50, 10)); // еще одно яблоко со скидкой

                System.out.println("1. Корзина до удаления:");
                demoBasket.printBasket();

                // Демонстрация удаления по имени
                System.out.println("2. Удаляем все товары с именем 'Яблоко':");
                List<Product> removedProducts = demoBasket.removeProductsByName("Яблоко");
                System.out.println("Удалено продуктов: " + removedProducts.size());
                for (Product removed : removedProducts) {
                        System.out.println(" - " + removed.getName() + " (" + removed.getClass().getSimpleName() + ")");
                }

                System.out.println("3. Корзина после удаления:");
                demoBasket.printBasket();

                // Демонстрация удаления несуществующего товара
                System.out.println("4. Пытаемся удалить несуществующий товар 'Манго':");
                List<Product> emptyList = demoBasket.removeProductsByName("Манго");
                System.out.println("Удалено продуктов: " + emptyList.size());
                if (emptyList.isEmpty()) {
                        System.out.println("Список удаленных продуктов пустой - товар не найден");
                }

                // Демонстрация неограниченного размера корзины
                System.out.println("5. Демонстрация неограниченного размера корзины:");
                ProductBasket bigBasket = new ProductBasket();
                for (int i = 1; i <= 10; i++) {
                        bigBasket.addProduct(new SimpleProduct("Товар" + i, i * 10));
                }
                System.out.println("Добавлено 10 товаров в корзину:");
                bigBasket.printBasket();

                // Демонстрация работы SearchEngine с List
                System.out.println("6. Демонстрация SearchEngine с List:");
                SearchEngine listSearchEngine = new SearchEngine();
                listSearchEngine.add(new SimpleProduct("Компьютер", 50000));
                listSearchEngine.add(new SimpleProduct("Компьютерный стол", 15000));
                listSearchEngine.add(new SimpleProduct("Мышь компьютерная", 2000));

                List<Searchable> searchResults = listSearchEngine.search("компьютер");
                System.out.println("Найдено результатов: " + searchResults.size());
                for (Searchable result : searchResults) {
                        System.out.println(" - " + result.getName());
                }
        }
}