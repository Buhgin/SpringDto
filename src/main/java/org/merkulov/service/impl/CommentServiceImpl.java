package org.merkulov.service.impl;

import lombok.RequiredArgsConstructor;
import org.merkulov.exception.BlogApiException;
import org.merkulov.modell.entity.Comment;
import org.merkulov.modell.entity.Post;
import org.merkulov.exception.ResourceNotFoundException;
import org.merkulov.modell.entity.User;
import org.merkulov.payload.DTO.CommentDTO;
import org.merkulov.payload.response.CommentResponse;
import org.merkulov.repository.CommentRepository;
import org.merkulov.repository.PostRepository;
import org.merkulov.repository.UserRepository;
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
    private final UserRepository userRepository;

    @Override
    public CommentDTO createComment(CommentDTO commentDTO, Long postId,Long userId) {
        Post post = postRepository.findById(postId).orElseThrow(() ->
                new ResourceNotFoundException("Post", "id", postId));
        User user = userRepository.findById(userId).orElseThrow(()->
                new ResourceNotFoundException("User","id",userId));
        Comment comment = mapToEntity(commentDTO);
        comment.setPost(post);
        comment.setUser(user);
        Comment newComment = commentRepository.save(comment);
        return mapToDTO(newComment);
    }

    @Override
    public CommentDTO updateComment(Long commentId,CommentDTO commentDTO)  {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() ->
                new ResourceNotFoundException("Comment", "id", commentId));
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
    public CommentResponse getCommentByUserid(Long userId, int pageNo, int pageSize, String sortBy, String sortDir) {
          User user = userRepository.findById(userId).orElseThrow(()->
                  new ResourceNotFoundException("User","id",userId));
          user.setPassword("xxxxxxx");
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending(): Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo,pageSize,sort);
        Page<Comment> comments =  commentRepository.findByUserId(user.getId(),pageable);
        List<Comment> commentList = comments.getContent();
        CommentResponse commentResponse = new CommentResponse();
        List<CommentDTO> listDto = commentList.stream().map(this::mapToDTO).toList();
        commentResponse.setContent(listDto);
        commentResponse.setPageNo(comments.getNumber());
        commentResponse.setPageSize(comments.getSize());
        commentResponse.setTotalElements(comments.getTotalElements());
        commentResponse.setTotalPages(comments.getTotalPages());
        commentResponse.setLast(comments.isLast());

        return commentResponse;
    }


    private Comment mapToEntity(CommentDTO commentDTO) {
        return modelMapper.map(commentDTO, Comment.class);
    }

    private CommentDTO mapToDTO(Comment newComment) {


        return modelMapper.map(newComment, CommentDTO.class);
    }
}
