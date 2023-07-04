package com.application.commentar.modal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Id;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Node
@Builder
public class Commentar {

    @Id
    private String id;

    private String username;
    private Long productId;
    private String text;
    private String commentarDate;
}
