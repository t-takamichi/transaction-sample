package com.transaction.transaction_sample.service;

import com.transaction.transaction_sample.dto.VocabularyDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class VocabularyServiceTest {
    @Autowired
    private VocabularyService vocabularyService;

    @Test
    public void testOK() {
        VocabularyDto vocabularyDto = vocabularyService.get("car");
        Assertions.assertEquals("車", vocabularyDto.getMeaning());
        vocabularyDto.setMeaning("クルマ");
        try {
            vocabularyService.update(vocabularyDto);
        } catch (RuntimeException e) {
            // Nothing
        }
        Assertions.assertEquals("車", vocabularyService.get("car").getMeaning());
    }
    @Test
    public void testOK2() {
        VocabularyDto dto = VocabularyDto.builder()
                .name("test")
                .example("test")
                .meaning("test")
                .build();
        vocabularyService.save(dto);
        Assertions.assertEquals("test", vocabularyService.get("test").getMeaning());
    }

    @Test
    public void test3() {
        try {
            vocabularyService.saveTest();
        } catch (Exception e) {

        }
        Assertions.assertThrows(RuntimeException.class, () -> vocabularyService.get("test_1"));

    }



}
