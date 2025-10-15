package org.skypro.skyshop;

import org.skypro.skyshop.article.Article;
import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.*;
import org.skypro.skyshop.search.SearchEngine;
import org.skypro.skyshop.search.Searchable;

import java.util.Set;

public class App {
    public static void main(String[] args) {
        System.out.println("=== ДОМАШНЯЯ РАБОТА 7: SET ===");

        // 1. СОЗДАНИЕ ПРОДУКТОВ РАЗНЫХ ТИПОВ
        System.out.println("\n1. СОЗДАНИЕ ПРОДУКТОВ:");

        SimpleProduct laptop = new SimpleProduct("Ноутбук игровой", 50000);
        SimpleProduct mouse = new SimpleProduct("Мышь компьютерная", 1000);
        DiscountedProduct keyboard = new DiscountedProduct("Клавиатура механическая", 2000, 10);
        FixPriceProduct headphones = new FixPriceProduct("Наушники беспроводные");

        System.out.println("✓ Созданы продукты: Ноутбук, Мышь, Клавиатура, Наушники");

        // 2. СОЗДАНИЕ СТАТЕЙ
        System.out.println("\n2. СОЗДАНИЕ СТАТЕЙ:");

        Article article1 = new Article("Обзор современных ноутбуков",
                "Современные ноутбуки обладают высокой производительностью и длительным временем работы от батареи...");
        Article article2 = new Article("Выбор мыши для гейминга",
                "Игровые мыши отличаются высокой точностью сенсора, эргономичной формой и программируемыми кнопками...");
        Article article3 = new Article("Преимущества механических клавиатур",
                "Механические клавиатуры обеспечивают тактильный отклик и долговечность...");

        System.out.println("✓ Созданы статьи: Обзор ноутбуков, Выбор мыши, Механические клавиатуры");

        // 3. РАБОТА С КОРЗИНОЙ (для демонстрации)
        System.out.println("\n3. ДЕМОНСТРАЦИЯ КОРЗИНЫ:");

        ProductBasket basket = new ProductBasket();
        basket.addProduct(laptop);
        basket.addProduct(mouse);
        basket.addProduct(keyboard);
        basket.addProduct(headphones);

        basket.printBasket();

        // 4. СОЗДАНИЕ И НАСТРОЙКА ПОИСКОВОГО ДВИЖКА
        System.out.println("\n4. НАСТРОЙКА ПОИСКОВОГО ДВИЖКА:");

        SearchEngine searchEngine = new SearchEngine();

        // Добавляем все продукты и статьи
        searchEngine.add(laptop);
        searchEngine.add(mouse);
        searchEngine.add(keyboard);
        searchEngine.add(headphones);
        searchEngine.add(article1);
        searchEngine.add(article2);
        searchEngine.add(article3);

        System.out.println("✓ В поисковый движок добавлены все продукты и статьи");

        // 5. ДЕМОНСТРАЦИЯ ПРЕДОТВРАЩЕНИЯ ДУБЛИКАТОВ
        System.out.println("\n5. ПРОВЕРКА ПРЕДОТВРАЩЕНИЯ ДУБЛИКАТОВ:");

        // Пытаемся добавить дубликаты
        SimpleProduct laptopDuplicate = new SimpleProduct("Ноутбук игровой", 45000);
        Article articleDuplicate = new Article("Обзор современных ноутбуков", "Другой текст...");

        boolean addedLaptop = searchEngine.add(laptopDuplicate);
        boolean addedArticle = searchEngine.add(articleDuplicate);

        System.out.println("Попытка добавить дубликат 'Ноутбук игровой': " + (addedLaptop ? "УСПЕШНО" : "ОТКЛОНЕНО"));
        System.out.println("Попытка добавить дубликат 'Обзор современных ноутбуков': " + (addedArticle ? "УСПЕШНО" : "ОТКЛОНЕНО"));
        System.out.println("Общее количество элементов в поисковом движке: " + searchEngine.getAll().size());

        // 6. ДЕМОНСТРАЦИЯ ПОИСКА С СОРТИРОВКОЙ
        System.out.println("\n6. ДЕМОНСТРАЦИЯ ПОИСКА:");

        // Поиск по слову "ноутбук"
        System.out.println("=== Поиск по слову 'ноутбук' ===");
        Set<Searchable> notebookResults = searchEngine.search("ноутбук");
        if (notebookResults.isEmpty()) {
            System.out.println("Результаты не найдены");
        } else {
            for (Searchable item : notebookResults) {
                System.out.println("• " + item.getStringRepresentation() +
                        " (длина имени: " + item.getName().length() + ")");
            }
        }

        // Поиск по слову "мышь"
        System.out.println("\n=== Поиск по слову 'мышь' ===");
        Set<Searchable> mouseResults = searchEngine.search("мышь");
        if (mouseResults.isEmpty()) {
            System.out.println("Результаты не найдены");
        } else {
            for (Searchable item : mouseResults) {
                System.out.println("• " + item.getStringRepresentation() +
                        " (длина имени: " + item.getName().length() + ")");
            }
        }

        // 7. ДЕМОНСТРАЦИЯ СОРТИРОВКИ ПО ДЛИНЕ ИМЕНИ
        System.out.println("\n7. ВСЕ ЭЛЕМЕНТЫ (ОТСОРТИРОВАНЫ ПО ДЛИНЕ ИМЕНИ):");

        Set<Searchable> allItems = searchEngine.getAll();
        for (Searchable item : allItems) {
            System.out.println("• " + item.getName() +
                    " (длина: " + item.getName().length() + ", тип: " + item.getContentType() + ")");
        }

        // 8. ПОИСК НЕСУЩЕСТВУЮЩЕГО ЭЛЕМЕНТА
        System.out.println("\n8. ПОИСК НЕСУЩЕСТВУЮЩЕГО ЭЛЕМЕНТА:");

        Set<Searchable> noResults = searchEngine.search("абсолютнонесуществующееслово");
        System.out.println("Результатов найдено: " + noResults.size());

        System.out.println("\n=== ДЕМОНСТРАЦИЯ ЗАВЕРШЕНА ===");
    }
}