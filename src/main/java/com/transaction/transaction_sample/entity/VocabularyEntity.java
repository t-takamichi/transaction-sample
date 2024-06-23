package com.transaction.transaction_sample.entity;

import com.transaction.transaction_sample.dto.VocabularyDto;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class VocabularyEntity {
    private final Long id;
    private String name;
    private String example;
    private String mean;
    private final Date createdAt;
    private final Date updatedAt;

    public VocabularyDto toDto() {
        return VocabularyDto.builder()
                .id(id)
                .name(name)
                .example(example)
                .mean(mean)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .build();
    }

}
