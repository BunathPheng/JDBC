package model.dto;
import lombok.Builder;


import java.sql.Date;
@Builder
public record CustomerDto(
        String name ,
        String email ,
        Boolean  isDeleted,
        Date createdAt
) {
}
