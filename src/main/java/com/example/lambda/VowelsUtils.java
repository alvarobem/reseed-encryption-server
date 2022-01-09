package com.example.lambda;

import javax.enterprise.context.ApplicationScoped;
import java.util.AbstractMap;
import java.util.Map;
import java.util.Optional;

@ApplicationScoped
public class VowelsUtils {

    Map<String, Integer> vowels = Map.ofEntries(
            new AbstractMap.SimpleEntry<>("a", 1), new AbstractMap.SimpleEntry<>("b", 2), new AbstractMap.SimpleEntry<>("c", 3),
            new AbstractMap.SimpleEntry<>("d", 4), new AbstractMap.SimpleEntry<>("e", 5), new AbstractMap.SimpleEntry<>("f", 6),
            new AbstractMap.SimpleEntry<>("g", 7), new AbstractMap.SimpleEntry<>("h", 8), new AbstractMap.SimpleEntry<>("i", 9),
            new AbstractMap.SimpleEntry<>("j", 10), new AbstractMap.SimpleEntry<>("k", 11), new AbstractMap.SimpleEntry<>("l", 12),
            new AbstractMap.SimpleEntry<>("m", 13), new AbstractMap.SimpleEntry<>("n", 14), new AbstractMap.SimpleEntry<>("o", 15),
            new AbstractMap.SimpleEntry<>("p", 16), new AbstractMap.SimpleEntry<>("q", 17), new AbstractMap.SimpleEntry<>("r", 18),
            new AbstractMap.SimpleEntry<>("s", 19), new AbstractMap.SimpleEntry<>("t", 20), new AbstractMap.SimpleEntry<>("u", 21),
            new AbstractMap.SimpleEntry<>("v", 22), new AbstractMap.SimpleEntry<>("w", 23), new AbstractMap.SimpleEntry<>("y", 24),
            new AbstractMap.SimpleEntry<>("z", 25)
    );

    public Integer getCharValue(String c){

        return Optional.ofNullable(vowels.get(c)).orElse(25);
    }

}
