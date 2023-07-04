package com.application.commentar.service;

import com.application.commentar.modal.Commentar;

import java.util.List;

public interface CommentarService {

    List<Commentar> createCommnetar(Long userId, Long productId, String commentar, String token);

    List<Commentar> getAllByProductId(Long productId);
}
