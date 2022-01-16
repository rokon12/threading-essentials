package org.jugbd.essentials;

import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

record WordCount(String word, long count) {
}

public class MostFrequentWordService {
    public List<WordCount> mostFrequentWord(String url) throws IOException {
        var wordCount = Arrays.stream(getWords(url))
                .filter(value -> !value.isEmpty())
                .filter(value -> value.length() > 3)
                .map(String::toLowerCase)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        return wordCount.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(5)
                .map(entry -> new WordCount(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    private String[] getWords(String url) throws IOException {
        var connect = Jsoup.connect(url);
        var document = connect.get();
        var content = document.body().text();
        return content.split("[^a-zA-Z]");
    }
}
