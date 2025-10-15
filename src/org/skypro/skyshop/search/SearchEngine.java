package org.skypro.skyshop.search;

import java.util.*;

/**
 * Поисковый движок
 */
public class SearchEngine {
    private List<Searchable> searchables;

    public SearchEngine() {
        this.searchables = new ArrayList<>();
    }

    public void add(Searchable searchable) {
        searchables.add(searchable);
    }

    public Map<String, Searchable> search(String searchString) {
        Map<String, Searchable> resultMap = new TreeMap<>();

        for (Searchable item : searchables) {
            if (item.getSearchTerm().toLowerCase().contains(searchString.toLowerCase())) {
                resultMap.put(item.getName(), item);
            }
        }

        return resultMap;
    }
}