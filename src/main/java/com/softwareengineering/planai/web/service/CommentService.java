package com.softwareengineering.planai.web.service;

import com.softwareengineering.planai.domain.entity.Comment;
import com.softwareengineering.planai.domain.entity.Post;
import com.softwareengineering.planai.web.dto.update.PostUpdateDto;
import com.softwareengineering.planai.web.repository.CommentRepository;
import com.softwareengineering.planai.web.repository.PostRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentService {

    private CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Transactional
    public Comment addComment(Comment comment) {
        commentRepository.save(comment);
        return comment;
    }

    @Transactional
    public Optional<Comment> findById(Long id) {
        return commentRepository.findById(id);
    }

    @Transactional
    public List<Comment> findByPost(Post post) {
        return commentRepository.findByPost(post);
    }

    @Transactional
    public Comment updateComment(Long id, String newContent) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("id에 대응되는 댓글을 찾을 수 없음"));
        comment.updateComment(newContent);
        return comment;
    }

    @Transactional
    public Long deleteComment(Long id) {
        commentRepository.deleteById(id);
        return id;
    }
}
