package com.example.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class DecryptLambdaHandlerTest {

    @Mock
    Context context;

    @Spy
    VowelsUtils vowelsUtils;

    @Spy
    WordsUtils wordsUtils;

    @InjectMocks
    DecryptLambda lambda;


    @Test
    void shouldTransformExistingWords() throws Exception {

        EncryptRequest request = new EncryptRequest();
        request.setKey("a");
        final var stringList = List.of("regret", "group", "reason", "there");
        request.setWords(stringList);
        final var expectedResult = List.of("reason", "regret", "there", "rack");;

        final var result = lambda.handleRequest(request, context);

        assertEquals(expectedResult.size(), result.getWords().size());
        assertEquals(expectedResult, result.getWords());
    }

    @Test
    void shouldNotTransformNoExistingWords() throws Exception {

        EncryptRequest request = new EncryptRequest();
        request.setKey("a");
        final var stringList = List.of("regret", "group", "casa", "perro");
        request.setWords(stringList);
        final var expectedResult = List.of("reason", "regret", "casa", "perro");;

        final var result = lambda.handleRequest(request, context);

        assertEquals(expectedResult.size(), result.getWords().size());
        assertEquals(expectedResult, result.getWords());
    }

}
