package model.dto;

import lombok.Builder;

import java.sql.Date;
@Builder
public record ProductDto(
        String productName,
        String productCode,
        Boolean isDeleted,
        Date importedAt,
        Date expiredAt,
        String proDescription
) {
}
