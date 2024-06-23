package com.transaction.transaction_sample.repository;

import java.util.Optional;

import com.transaction.transaction_sample.entity.VocabularyEntity;

public interface VocabularyRepository {
    Optional<VocabularyEntity> findByName(String name);
    Optional<VocabularyEntity> findById(Long id);
    Optional<VocabularyEntity> insert(VocabularyEntity vocabularyEntity);
    Optional<VocabularyEntity> update(VocabularyEntity vocabularyEntity);
}
