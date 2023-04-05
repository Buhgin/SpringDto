package org.merkulov.service.impl;

import lombok.RequiredArgsConstructor;
import org.merkulov.exception.BlogApiException;
import org.merkulov.modell.entity.Comment;
import org.merkulov.modell.entity.Post;
import org.merkulov.exception.ResourceNotFoundException;
import org.merkulov.payload.DTO.CommentDTO;
import org.merkulov.payload.response.CommentResponse;
import org.merkulov.repository.CommentRepository;
import org.merkulov.repository.PostRepository;
import org.merkulov.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    @Override
    public CommentDTO createComment(CommentDTO commentDTO, Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() ->
                new ResourceNotFoundException("Post", "id", postId));
        Comment comment = mapToEntity(commentDTO);
        comment.setPost(post);
        Comment newComment = commentRepository.save(comment);
        return mapToDTO(newComment);
    }

    @Override
    public CommentDTO updateComment(Long postId,Long commentId,CommentDTO commentDTO) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() ->
                new ResourceNotFoundException("Comment", "id", commentId));
        Post post = postRepository.findById(postId).orElseThrow(() ->
                new ResourceNotFoundException("Post", "id", postId));

      if (!comment.getPost().getId().equals(post.getId())) {
        System.out.printf("comment postID %d,    postId %d",comment.getPost().getId(),post.getId());
           throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment does not belong to the post");
        }
        comment.setPost(post);
        comment.setBody(commentDTO.getBody());
        comment.setName(commentDTO.getName());
        comment.setEmail(commentDTO.getEmail());
        Comment commentSave = commentRepository.save(comment);

        return mapToDTO(commentSave);
    }

    @Override
    public CommentResponse getCommentsByPostId(Long postId, int pageNo, int pageSize, String sortBy, String sortDir) {

        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        // получаем из бд отсортированный список комментариев зависящих от пост id
        Page<Comment> comemnts = commentRepository.findByPostId(post.getId(),pageable);
        List<Comment> commentList =comemnts.getContent();
        List<CommentDTO> commentDTOS =commentList.stream().map(this::mapToDTO).toList();
        CommentResponse commentResponse = new CommentResponse();
        commentResponse.setContent(commentDTOS);
        commentResponse.setPageNo(comemnts.getNumber());
        commentResponse.setPageSize(comemnts.getSize());
        commentResponse.setTotalElements(comemnts.getTotalElements());
        commentResponse.setTotalPages(comemnts.getTotalPages());
        commentResponse.setLast(comemnts.isLast());



        return commentResponse;

    }

    @Override
    public void deleteComment(Long id) {
        commentRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Comment", "id", id));
        commentRepository.deleteById(id);

    }

    @Override
    public CommentResponse getCommentByPostId(Long postId, Long commentId) {

        return null;
    }


    private Comment mapToEntity(CommentDTO commentDTO) {
        return modelMapper.map(commentDTO, Comment.class);
    }

    private CommentDTO mapToDTO(Comment newComment) {


        return modelMapper.map(newComment, CommentDTO.class);
    }
}
