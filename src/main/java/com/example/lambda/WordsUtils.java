package com.example.lambda;


import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@ApplicationScoped
public class WordsUtils {

    private static final Logger LOG = Logger.getLogger(WordsUtils.class);

    public static final String SPACE = " ";

    Map<String, Integer> wordsPosition = new HashMap<>();

    Map<Integer, String> positionWords = new HashMap<>();

    public WordsUtils() {
        String file = "words.txt";
        try {
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            InputStream is = classloader.getResourceAsStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            reader.lines().forEach(this::addToVowels);
            reader.close();
        } catch (Exception e) {
            LOG.error("Can't read file");
        }

    }

    private void addToVowels(String s) {

        final var splited = s.split(SPACE);
        wordsPosition.put(splited[0], Integer.valueOf(splited[1]));
        positionWords.put(Integer.valueOf(splited[1]), splited[0]);
    }

    public String getWordByPosition(Integer pos){
        return positionWords.get(pos);
    }

    public Integer getPositionByWord(String word){
        return wordsPosition.get(word);
    }

    public Integer getWordLength(){
        return wordsPosition.entrySet().size();
    }

}
