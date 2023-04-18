package org.merkulov.service.impl;

import lombok.RequiredArgsConstructor;
import org.merkulov.exception.ResorceNotFoundExeptionByName;
import org.merkulov.modell.entity.Post;
import org.merkulov.exception.ResourceNotFoundException;
import org.merkulov.modell.entity.Role;
import org.merkulov.modell.entity.User;
import org.merkulov.payload.DTO.PostDTO;
import org.merkulov.payload.response.PostResponse;
import org.merkulov.repository.PostRepository;
import org.merkulov.repository.UserRepository;
import org.merkulov.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;


    @Override
    public PostDTO createPost(PostDTO postDto, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()->
                new ResourceNotFoundException("User","id",userId));
        Post post = mapToEntity(postDto);
        post.setUser(user);
        Post newPost = postRepository.save(post);
        return mapToDTO(newPost);
    }

    @Override
    public PostDTO updatePost(Long id, PostDTO postDto) {
        boolean b = exampleMethod(id);
        if(b) {

            Post post = postRepository.findById(id).orElseThrow(() ->
                    new ResourceNotFoundException("Post", "id", id));
            post.setTitle(postDto.getTitle());
            post.setDescription(postDto.getDescription());
            post.setContent(postDto.getContent());
            Post updatedPost = postRepository.save(post);
            return mapToDTO(updatedPost);
        }
        System.out.println("нехера не работает!!!  "+b);
    return null;}

    @Override
    public void deletePost(Long id) {
        if(exampleMethod(id)){
        Post post = postRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Post", "id", id));
        postRepository.delete(post);}
    }
    @Override
    public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Post> posts = postRepository.findAll(pageable);
        List<Post> listOfPost = posts.getContent();
        List<PostDTO> postDTOList = listOfPost.stream().map(this::mapToDTO).toList();
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDTOList);
        postResponse.setPageNo(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setLast(posts.isLast());

        return postResponse;
    }

    @Override
    public PostResponse getPostByUserId(Long userid, int pageNo, int pageSize, String sortBy, String sortDir) {
        User user = userRepository.findById(userid).orElseThrow(()->
                new ResourceNotFoundException("User","id",userid));
        user.setPassword("xxxxxxx");
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending(): Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo,pageSize,sort);
        Page<Post> posts =  postRepository.findByUserId(user.getId(),pageable);
        List<Post> postList = posts.getContent();
        PostResponse postResponse = new PostResponse();
        List<PostDTO> listDto = postList.stream().map(this::mapToDTO).toList();
        postResponse.setContent(listDto);
        postResponse.setPageNo(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setLast(posts.isLast());
return postResponse;
    }

    @Override
    public PostDTO getPostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Post", "id", id));
        return mapToDTO(post);
    }

    private Post mapToEntity(PostDTO postDto) {
        return modelMapper.map(postDto, Post.class);
    }

    private PostDTO mapToDTO(Post newPost) {return modelMapper.map(newPost, PostDTO.class);
    }
    private boolean exampleMethod(Long postId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
     User user = userRepository.findByEmail(currentUserName).orElseThrow(()->
               new ResorceNotFoundExeptionByName("user","name",currentUserName));
        System.out.println(user.getId()+"User id example method");
        Post post = postRepository.findById(postId).orElseThrow(() ->
                new ResourceNotFoundException("Post", "id", postId));


     if(post.getUser().getId().equals(user.getId()))
           return true ;
      for(Role role:user.getRoles()){
          if(role.getName().equals("ROLE_ADMIN")||role.getName().equals("ROLE_MANAGER")){
              return true;
          }
      }

          return false;
    }
}
