package org.merkulov.controller;

import lombok.*;
import org.merkulov.payload.DTO.CommentDTO;
import org.merkulov.payload.response.CommentResponse;
import org.merkulov.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/posts/{postId}/comments/users{userId}")
    public ResponseEntity<CommentDTO> createCommentDTO(@PathVariable(value = "postId") Long postId
            , @PathVariable(value = "userId") Long userId
            , @Valid @RequestBody CommentDTO commentDTO) {

        CommentDTO commentDTOcreate = commentService.createComment(commentDTO, postId, userId);

        return new ResponseEntity<>(commentDTOcreate, HttpStatus.CREATED);
    }

    @PutMapping("/posts/{postId}/comments/{commentId}")

    public ResponseEntity<CommentDTO> updateCommentDTO(@PathVariable(value = "commentId") Long commentId,

                                                       @Valid @RequestBody CommentDTO commentDTO) {

        CommentDTO commentResponse = commentService.updateComment(commentId, commentDTO);
        return new ResponseEntity<>(commentResponse, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public void deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
    }

    @GetMapping("/post/{id}/comments")
    public CommentResponse getAllByPage(@PathVariable(name = "id") Long postId,
                                        @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
                                        @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
                                        @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
                                        @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {


        return commentService.getCommentsByPostId(postId, pageNo, pageSize, sortBy, sortDir);
    }

    @GetMapping("/user/{userId}/comments")
    public CommentResponse getAllByPageUserID(@PathVariable(name = "userId") Long userId,
                                              @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
                                              @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
                                              @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
                                              @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {


        return commentService.getCommentByUserid(userId, pageNo, pageSize, sortBy, sortDir);
    }


}

