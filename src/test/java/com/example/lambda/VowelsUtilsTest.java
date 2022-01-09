package com.example.lambda;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class VowelsUtilsTest {

    @InjectMocks
    VowelsUtils vowelsUtils;

    @Test
    void shouldReturnCorrectValueWithoutNumbers() {

        final var charValue = vowelsUtils.getCharValue("b");

        assertEquals(2, charValue);

    }

}
