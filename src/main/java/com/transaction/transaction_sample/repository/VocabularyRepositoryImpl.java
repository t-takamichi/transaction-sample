package com.transaction.transaction_sample.repository;

import com.transaction.transaction_sample.entity.VocabularyEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class VocabularyRepositoryImpl implements VocabularyRepository {

    private final JdbcClient jdbcClient;
    @Override
    public Optional<VocabularyEntity> findByName(String name) {
        Optional<VocabularyEntity> vocabularyEntity= jdbcClient.sql("""
                    SELECT id, name, meaning, example, created_at, updated_at
                    FROM VOCABULARY 
                    WHERE name = :name
                    """)
                .param("name", name)
                .query(new DataClassRowMapper<>(VocabularyEntity.class))
                .optional();
        return vocabularyEntity;
    }

    @Override
    public Optional<VocabularyEntity> findById(Long id) {
        Optional<VocabularyEntity> vocabularyEntity= jdbcClient.sql("""
                    SELECT id, name, meaning, example, created_at, updated_at
                    FROM VOCABULARY 
                    WHERE id = :id
                    """)
                .param("id", id)
                .query(new DataClassRowMapper<>(VocabularyEntity.class))
                .optional();
        return vocabularyEntity;
    }

    @Override
    public Optional<VocabularyEntity> insert(VocabularyEntity vocabularyEntity) {
        jdbcClient.sql("""
                    INSERT INTO VOCABULARY(name,meaning,example) VALUES(:name, :meaning, :example)
                    """)
                .param("name", vocabularyEntity.getName())
                .param("meaning", vocabularyEntity.getMeaning())
                .param("example", vocabularyEntity.getExample())
                .update();

        return this.findByName(vocabularyEntity.getName());
    }

    @Override
    public Optional<VocabularyEntity> update(VocabularyEntity vocabularyEntity) {
        jdbcClient.sql("""
                    UPDATE VOCABULARY
                    SET name = :name, meaning = :meaning, example = :example
                    WHERE id = :id
                    """)
                .param("name", vocabularyEntity.getName())
                .param("meaning", vocabularyEntity.getMeaning())
                .param("example", vocabularyEntity.getExample())
                .param("id", vocabularyEntity.getId())
                .update();

        return this.findById(vocabularyEntity.getId());
    }
}
