package com.example.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

@Named("encrypt")
public class EncryptLambda implements RequestHandler<EncryptRequest, EncryptResponse> {

    @Inject
    VowelsUtils vowelsUtils;

    @Inject
    WordsUtils wordsUtils;

    @Override
    public EncryptResponse handleRequest(EncryptRequest input,
                                         Context context) {

        final var keyValue = Arrays.stream(input.getKey().split("")).map(vowelsUtils::getCharValue)
                .reduce(0, Integer::sum);

        final var mirrorWords = input.getWords().stream().map(w -> calculateMirrorWord(w, keyValue)).collect(Collectors.toList());

        var response = new EncryptResponse();
        response.setWords(mirrorWords);
        return response;
    }

    private String calculateMirrorWord(String s,
                                       Integer keyValue) {

        return Optional.ofNullable(wordsUtils.getPositionByWord(s))
                .map(p -> getMirrorWord(p, keyValue))
                .orElse(s);
    }

    private String getMirrorWord(Integer pos, Integer keyValue) {
        final var getTotalWords = wordsUtils.getWordLength();
        final var newPosition = (pos + keyValue) % getTotalWords;
        return wordsUtils.getWordByPosition(newPosition);
    }
}
