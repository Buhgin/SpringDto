package org.merkulov.service;


import org.merkulov.payload.DTO.PostDTO;
import org.merkulov.payload.response.CommentResponse;
import org.merkulov.payload.response.PostResponse;

public interface PostService {

    PostDTO createPost(PostDTO postDto, Long userId);
    PostDTO updatePost(Long id, PostDTO postDto);
    PostDTO getPostById(Long id);
    void deletePost(Long id);
    PostResponse getAllPosts(int pageNo,int pageSize,String sortBy,String sortDir);
    PostResponse getPostByUserId(Long userid, int pageNo, int pageSize, String sortBy, String sortDir);

}
