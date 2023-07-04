package com.application.commentar.controller;

import com.application.commentar.modal.Commentar;
import com.application.commentar.security.TokenProvider;
import com.application.commentar.service.CommentarService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/commentars")
public class CommentarController {
    private final TokenProvider tokenProvider;
    private final CommentarService commentarService;

    @PostMapping("/create")
    public List<Commentar> createCommentar(@RequestParam("commentar") String commentar, @RequestParam("productId")  Long productId, HttpServletRequest request) {
        String token = "Bearer " + tokenProvider.getToken(request);
        return commentarService.createCommnetar(tokenProvider.extractUser(request).getId(), productId, commentar, token);
    }

    @GetMapping("/all")
    public List<Commentar> getCommentars(@RequestParam("productId") Long productId) {
        return commentarService.getAllByProductId(productId);
    }
}
