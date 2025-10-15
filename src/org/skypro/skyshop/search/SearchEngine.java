package org.skypro.skyshop.search;

import java.util.*;

public class SearchEngine {
    // ЗАМЕНА LIST НА SET ДЛЯ ПРЕДОТВРАЩЕНИЯ ДУБЛИКАТОВ
    private final Set<Searchable> storage;

    public SearchEngine() {
        // Используем HashSet для хранения - дубликаты не допускаются
        this.storage = new HashSet<>();
    }

    /**
     * Добавляет элемент в поисковый движок
     * @param item элемент для добавления
     * @return true если элемент добавлен, false если такой элемент уже существует
     */
    public boolean add(Searchable item) {
        return storage.add(item);
    }

    /**
     * Выполняет поиск по всем элементам
     * @param pattern строка для поиска
     * @return отсортированный TreeSet с результатами
     */
    public Set<Searchable> search(String pattern) {
        // Используем TreeSet с нашим компаратором для автоматической сортировки
        Set<Searchable> result = new TreeSet<>(new SearchableComparator());

        // Перебираем все элементы и добавляем подходящие
        for (Searchable item : storage) {
            if (item.getSearchTerm().toLowerCase().contains(pattern.toLowerCase())) {
                result.add(item);
            }
        }

        return result;
    }

    /**
     * Возвращает все элементы поискового движка
     * @return множество всех элементов
     */
    public Set<Searchable> getAll() {
        // Возвращаем копию для защиты от внешних изменений
        return new HashSet<>(storage);
    }

    /**
     * Возвращает количество элементов в движке
     * @return количество элементов
     */
    public int size() {
        return storage.size();
    }
}