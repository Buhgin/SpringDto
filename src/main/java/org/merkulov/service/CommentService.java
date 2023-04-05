package org.merkulov.service;

import org.merkulov.payload.DTO.CommentDTO;
import org.merkulov.payload.response.CommentResponse;

public interface CommentService {
    CommentDTO createComment(CommentDTO commentDTO, Long idPostDTO);

    CommentDTO updateComment(Long postId,Long commentId,CommentDTO commentDTO);

    CommentResponse getCommentsByPostId(Long postId, int pageNo, int pageSize, String sortBy, String sortDir);

    void deleteComment(Long id);

    CommentResponse getCommentByPostId(Long postId, Long commentId);


}
