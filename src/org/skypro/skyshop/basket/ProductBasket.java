package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;

import java.util.*;

/**
 * Класс корзины для товаров с использованием Map для хранения продуктов
 */
public class ProductBasket {
    private Map<String, List<Product>> productsMap;

    public ProductBasket() {
        this.productsMap = new HashMap<>();
    }

    public void addProduct(Product product) {
        String productName = product.getName();

        if (!productsMap.containsKey(productName)) {
            productsMap.put(productName, new ArrayList<>());
        }

        productsMap.get(productName).add(product);
    }

    public int getTotalPrice() {
        return productsMap.values().stream()
                .flatMap(List::stream)
                .mapToInt(Product::getPrice)
                .sum();
    }

    public void printBasket() {
        if (productsMap.isEmpty()) {
            System.out.println("в корзине пусто");
            return;
        }

        productsMap.forEach((productName, productList) -> {
            for (Product product : productList) {
                System.out.println(product);
            }
        });

        long specialCount = getSpecialCount();

        System.out.println("Итого: " + getTotalPrice());
        System.out.println("Специальных товаров: " + specialCount);
    }

    public boolean containsProduct(String name) {
        return productsMap.containsKey(name) && !productsMap.get(name).isEmpty();
    }

    public void clear() {
        productsMap.clear();
    }

    public List<Product> removeProductsByName(String name) {
        if (!productsMap.containsKey(name)) {
            return new ArrayList<>();
        }

        List<Product> removedProducts = productsMap.get(name);
        productsMap.remove(name);
        return removedProducts;
    }

    private long getSpecialCount() {
        return productsMap.values().stream()
                .flatMap(List::stream)
                .filter(Product::isSpecial)
                .count();
    }
}