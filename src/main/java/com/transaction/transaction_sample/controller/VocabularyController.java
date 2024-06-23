package com.transaction.transaction_sample.controller;

import com.transaction.transaction_sample.dto.VocabularyDto;
import com.transaction.transaction_sample.service.VocabularyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/vocabulary")
@RequiredArgsConstructor
public class VocabularyController {
    private final VocabularyService vocabularyService;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public VocabularyDto get(@RequestParam("query") String query) {
        return vocabularyService.get(query);
    }
}
