package com.transaction.transaction_sample.repository;

import com.transaction.transaction_sample.entity.VocabularyEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class VocabularyRepositoryImpl implements VocabularyRepository {

    private final JdbcClient jdbcClient;
    @Override
    public Optional<VocabularyEntity> findByName(String name) {
        Optional<VocabularyEntity> vocabularyEntity= jdbcClient.sql("""
                    SELECT id, name, mean, example, created_at, updated_at
                    FROM VOCABULARY 
                    WHERE name = ?
                    """)
                .param(name)
                .query(new DataClassRowMapper<>(VocabularyEntity.class))
                .optional();
        return vocabularyEntity;
    }

    @Override
    public Optional<VocabularyEntity> findById(Long id) {
        Optional<VocabularyEntity> vocabularyEntity= jdbcClient.sql("""
                    SELECT id, name, mean, example, created_at, updated_at
                    FROM VOCABULARY 
                    WHERE id = ?
                    """)
                .param(id)
                .query(new DataClassRowMapper<>(VocabularyEntity.class))
                .optional();
        return vocabularyEntity;
    }

    @Override
    public Optional<VocabularyEntity> insert(VocabularyEntity vocabularyEntity) {
        jdbcClient.sql("""
                    INSERT INTO VOCABULARY(name,mean,example) VALUES(?,?,?)
                    """)
                .params(
                        List.of(
                                vocabularyEntity.getName(),
                                vocabularyEntity.getMean(),
                                vocabularyEntity.getExample()
                                )
                ).update();

        return this.findByName(vocabularyEntity.getName());
    }

    @Override
    public Optional<VocabularyEntity> update(VocabularyEntity vocabularyEntity) {
        jdbcClient.sql("""
                    UPDATE VOCABULARY
                    SET name = ?, mean = ?, example = ?
                    WHERE id = ?
                    """)
                .params(
                        List.of(
                                vocabularyEntity.getName(),
                                vocabularyEntity.getMean(),
                                vocabularyEntity.getExample(),
                                vocabularyEntity.getId()
                        )
                ).update();

        return this.findById(vocabularyEntity.getId());
    }
}
