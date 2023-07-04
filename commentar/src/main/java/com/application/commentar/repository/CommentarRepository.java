package com.application.commentar.repository;

import com.application.commentar.modal.Commentar;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentarRepository extends Neo4jRepository<Commentar, String>{
    List<Commentar> findAllByProductId(Long productId);
}
