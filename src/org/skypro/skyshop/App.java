import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.SimpleProduct;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;

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
        FixPriceProduct butter = new FixPriceProduct("Масло"); // Этот не добавится из-за лимита

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

        // 2. Попытка добавления в заполненную корзину
        basket.addProduct(butter); // Должно появиться сообщение "Невозможно добавить продукт"

        // 3. Печать содержимого корзины с несколькими товарами
        System.out.println("--- Корзина с товарами ---");
        basket.printBasket();

        // 4. Получение стоимости корзины с несколькими товарами
        System.out.println("--- Общая стоимость ---");
        System.out.println(basket.getTotalPrice());

        // 5. Поиск товара, который есть в корзине
        System.out.println("--- Поиск 'Хлеб' (есть) ---");
        System.out.println(basket.containsProduct("Хлеб"));

        // 6. Поиск товара, которого нет в корзине
        System.out.println("--- Поиск 'Колбаса' (нет) ---");
        System.out.println(basket.containsProduct("Колбаса"));

        // 7. Очистка корзины
        basket.clearBasket();

        // 8. Печать содержимого пустой корзины
        System.out.println("--- Пустая корзина ---");
        basket.printBasket();

        // 9. Получение стоимости пустой корзины
        System.out.println("--- Стоимость пустой корзины ---");
        System.out.println(basket.getTotalPrice());

        // 10. Поиск товара по имени в пустой корзине
        System.out.println("--- Поиск в пустой корзине ---");
        System.out.println(basket.containsProduct("Молоко"));
    }
}