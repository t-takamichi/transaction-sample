package com.transaction.transaction_sample.service;

import com.transaction.transaction_sample.dto.VocabularyDto;
import com.transaction.transaction_sample.entity.VocabularyEntity;
import com.transaction.transaction_sample.repository.VocabularyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class VocabularyService {
    private final VocabularyRepository vocabularyRepository;

    public VocabularyDto get(String query) {
        return vocabularyRepository.
                findByName(query)
                .orElseThrow(() -> new RuntimeException("Does't find query="+query))
                .toDto();
    }

    @Transactional
    public VocabularyDto update(VocabularyDto vocabularyDto) {
        VocabularyEntity vocabularyEntity = vocabularyRepository
                .findById(vocabularyDto.getId())
                .orElseThrow(() -> new RuntimeException("Does't find id="+vocabularyDto.getId()));
        vocabularyEntity.setExample(vocabularyDto.getExample());
        vocabularyEntity.setName(vocabularyDto.getName());
        vocabularyEntity.setMeaning(vocabularyDto.getMeaning());
        return vocabularyRepository.update(vocabularyEntity).get().toDto();
    }
    @Transactional
    public VocabularyDto save(VocabularyDto vocabularyDto) {
        VocabularyEntity vocabularyEntity = VocabularyEntity.builder()
                .name(vocabularyDto.getName())
                .meaning(vocabularyDto.getMeaning())
                .example(vocabularyDto.getExample())
                .build();
        return vocabularyRepository.insert(vocabularyEntity).get().toDto();
    }

    @Transactional
    public void saveTest() {
        for (int i = 1; i < 20; i++) {
            if (i == 3) {
                throw new RuntimeException("XXXXXXX");
            }
            VocabularyEntity vocabularyEntity = VocabularyEntity.builder()
                    .name("test_" + i)
                    .meaning("test_" + i)
                    .example("test_" + i)
                    .build();
            vocabularyRepository.insert(vocabularyEntity);
        }
    }

}
