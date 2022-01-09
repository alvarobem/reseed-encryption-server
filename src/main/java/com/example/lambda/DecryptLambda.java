package com.example.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

@Named("decrypt")
public class DecryptLambda implements RequestHandler<EncryptRequest, EncryptResponse> {

    @Inject
    VowelsUtils vowelsUtils;

    @Inject
    WordsUtils wordsUtils;

    @Override
    public EncryptResponse handleRequest(EncryptRequest input,
                                         Context context) {

        final var keyValue = Arrays.stream(input.getKey().split("")).map(vowelsUtils::getCharValue)
                .reduce(0, Integer::sum);

        final var originalWords = input.getWords().stream().map(w -> calculateOriginalWord(w, keyValue)).collect(Collectors.toList());

        var response = new EncryptResponse();
        response.setWords(originalWords);
        return response;
    }

    private String calculateOriginalWord(String s,
                                         Integer keyValue) {

        return Optional.ofNullable(wordsUtils.getPositionByWord(s))
                .map(p -> getMirrorWord(p, keyValue))
                .orElse(s);

    }

    private String getMirrorWord(Integer pos, Integer keyValue) {
        final var getTotalWords = wordsUtils.getWordLength();
        var newPosition = (pos - keyValue) % getTotalWords;
        if (newPosition < 0) {
            newPosition = getTotalWords + newPosition;
        }
        return wordsUtils.getWordByPosition(newPosition);
    }
}
