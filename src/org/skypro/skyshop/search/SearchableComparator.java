package org.skypro.skyshop.search;

import java.util.Comparator;

/**
 * Компаратор для сортировки Searchable объектов:
 * 1. По длине имени (от большего к меньшему)
 * 2. При равной длине - в натуральном порядке (алфавитном)
 */
public class SearchableComparator implements Comparator<Searchable> {

    @Override
    public int compare(Searchable o1, Searchable o2) {
        // Первичное сравнение: по длине имени (обратный порядок - от большего к меньшему)
        int lengthComparison = Integer.compare(
                o2.getName().length(),
                o1.getName().length()
        );

        // Если длины разные, возвращаем результат сравнения длин
        if (lengthComparison != 0) {
            return lengthComparison;
        }

        // Если длины одинаковые, сравниваем по алфавиту (натуральный порядок)
        return o1.getName().compareTo(o2.getName());
    }
}