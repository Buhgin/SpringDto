package org.merkulov.service;


import org.merkulov.payload.DTO.PostDTO;
import org.merkulov.payload.response.PostResponse;

public interface PostService {

    PostDTO createPost(PostDTO postDto);
    PostDTO updatePost(Long id, PostDTO postDto);
    PostDTO getPostById(Long id);
    void deletePost(Long id);
    PostResponse getAllPosts(int pageNo,int pageSize,String sortBy,String sortDir);


}
