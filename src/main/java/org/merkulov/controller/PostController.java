package org.merkulov.controller;

import lombok.RequiredArgsConstructor;
import org.merkulov.payload.DTO.PostDTO;
import org.merkulov.payload.response.PostResponse;
import org.merkulov.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {
    private final PostService postService;

    @PostMapping//("users/{id}")
    public ResponseEntity<PostDTO> createPost(//@PathVariable(value = "userId") Long postId,
                                              @Valid @RequestBody PostDTO postDto) {
        PostDTO post = postService.createPost(postDto);

        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }

    @GetMapping()
    public PostResponse findAll(@Valid
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir
    ) {
        return postService.getAllPosts(pageNo, pageSize, sortBy, sortDir);
    }

    @GetMapping("{id}")
   @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PostDTO> findId(@Valid @PathVariable Long id) {
        PostDTO postDTO = postService.getPostById(id);
        return new ResponseEntity<>(postDTO, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<PostDTO> updatePost(@Valid @RequestBody PostDTO postDto, @PathVariable(name = "id") Long id) {
        PostDTO postResponse = postService.updatePost(id, postDto);
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public void deletePost(@Valid @PathVariable Long id) {
        postService.deletePost(id);

    }

}


