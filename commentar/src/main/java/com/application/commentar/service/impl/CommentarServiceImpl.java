package com.application.commentar.service.impl;

//import com.application.commentar.client.UserClient;
import com.application.commentar.client.UserClient;
import com.application.commentar.modal.Commentar;
import com.application.commentar.repository.CommentarRepository;
import com.application.commentar.service.CommentarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommentarServiceImpl implements CommentarService {

    private final CommentarRepository commentarRepository;
    private final UserClient userClient;

    @Override
    public List<Commentar> createCommnetar(Long userId, Long productId, String commentar, String token) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Commentar newCommentar = Commentar.builder()
                .id(UUID.randomUUID().toString())
                .username(userClient.findUser(token, userId))
                .productId(productId)
                .text(commentar)
                .commentarDate(dateFormat.format(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant())))
                .build();

        commentarRepository.save(newCommentar);
        return getAllByProductId(productId);
    }

    @Override
    public List<Commentar> getAllByProductId(Long productId) {
        return commentarRepository.findAllByProductId(productId);
    }
}
