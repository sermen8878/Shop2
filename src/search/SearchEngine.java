import org.skypro.skyshop.exception.BestResultNotFound;
public Searchable findBestMatch(String search) throws BestResultNotFound {
    Searchable bestMatch = null;
    int maxOccurrences = 0;

    for (int i = 0; i < count; i++) {
        String term = searchables[i].getSearchTerm();
        int occurrences = countOccurrences(term, search);

        if (occurrences > maxOccurrences) {
            maxOccurrences = occurrences;
            bestMatch = searchables[i];
        }
    }

    if (bestMatch == null) {
        throw new BestResultNotFound(search);
    }

    return bestMatch;
}

private int countOccurrences(String text, String substring) {
    int count = 0;
    int index = 0;

    while ((index = text.indexOf(substring, index)) != -1) {
        count++;
        index += substring.length();
    }

    return count;
}