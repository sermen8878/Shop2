package org.skypro.skyshop.search;

/**
 * Интерфейс для поиска
 */
public interface Searchable {
    String getSearchTerm();
    String getContentType();
    String getName();

    default String getStringRepresentation() {
        return getName() + " - " + getContentType();
    }
}