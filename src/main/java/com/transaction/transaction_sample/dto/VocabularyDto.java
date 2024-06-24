package com.transaction.transaction_sample.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class VocabularyDto {
    private final Long id;
    private String name;
    private String example;
    private String meaning;
    private final Date createdAt;
    private final Date updatedAt;
}
