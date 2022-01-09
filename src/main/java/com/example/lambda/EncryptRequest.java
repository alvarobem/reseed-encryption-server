package com.example.lambda;

import java.util.List;

public class EncryptRequest {

    private String key;

    private List<String> words;

    public String getKey() {

        return key;
    }

    public void setKey(String key) {

        this.key = key;
    }

    public List<String> getWords() {

        return words;
    }

    public void setWords(List<String> words) {

        this.words = words;
    }
}
