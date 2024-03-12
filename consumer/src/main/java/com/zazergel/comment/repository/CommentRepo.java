package com.zazergel.comment.repository;

import com.zazergel.comment.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepo extends JpaRepository<Comment, Long> {

    List<Comment> findByProductId(Long productId);

    List<Comment> findByProductIdIn(List<Long> productIds);
}
