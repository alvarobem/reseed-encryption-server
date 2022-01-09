package com.example.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class EncryptLambdaHandlerTest {

    @Mock
    Context context;

    @Spy
    VowelsUtils vowelsUtils;

    @Spy
    WordsUtils wordsUtils;

    @InjectMocks
    EncryptLambda lambda;


    @Test
    void shouldTransformExistingWords() throws Exception {

        EncryptRequest request = new EncryptRequest();
        request.setKey("a");
        final var stringList = List.of("reason", "regret", "there", "rack");
        request.setWords(stringList);
        final var expectedResult = List.of("regret", "group", "reason", "there");

        final var result = lambda.handleRequest(request, context);

        assertEquals(expectedResult.size(), result.getWords().size());
        assertEquals(expectedResult, result.getWords());
    }

    @Test
    void shouldNotTransformNoExistingWords() throws Exception {

        EncryptRequest request = new EncryptRequest();
        request.setKey("a");
        final var stringList = List.of("reason", "regret", "perro", "casa");
        request.setWords(stringList);
        final var expectedResult = List.of("regret", "group", "perro", "casa");

        final var result = lambda.handleRequest(request, context);

        assertEquals(expectedResult.size(), result.getWords().size());
        assertEquals(expectedResult, result.getWords());
    }

}
