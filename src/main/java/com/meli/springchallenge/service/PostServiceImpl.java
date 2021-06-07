package com.meli.springchallenge.service;

import com.meli.springchallenge.converter.DTOConverter;
import com.meli.springchallenge.dto.NewPostDTO;
import com.meli.springchallenge.dto.PostFeedDTO;
import com.meli.springchallenge.dto.PostFeedPostDTO;
import com.meli.springchallenge.dto.UserBasicDTO;
import com.meli.springchallenge.exception.user.UserNotFoundException;
import com.meli.springchallenge.model.Post;
import com.meli.springchallenge.model.User;
import com.meli.springchallenge.model.UserBasic;
import com.meli.springchallenge.repository.PostRepository;
import com.meli.springchallenge.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PostRepository postRepository;

    @Override
    public void createPost(NewPostDTO newPostDTO) {
        User seller = userRepository.findUserByUserId(newPostDTO.getUserId());
        if (seller != null && seller.isSeller()) {
            postRepository.save(DTOConverter.newPostDTOToPost(newPostDTO));
        }
        else{
            throw new UserNotFoundException();
        }
    }

    @Override
    public PostFeedDTO getUserPostsByUserId(Integer userId, Optional<String> order) {
        PostFeedDTO postFeedDto = new PostFeedDTO();
        postFeedDto.setUserId(userId);

        User buyer = userRepository.findUserByUserId(userId);
        Set<UserBasic> followed = buyer.getFollowedSet();
        List<Integer> sellersIds = new ArrayList<>();
        followed.forEach(seller -> sellersIds.add(seller.getUserId()));
        List<Set<Post>> sellerPosts = postRepository.findPostsByUserIdBulk(sellersIds);

        List<Post> feedPosts = new ArrayList<>();
        LocalDate twoWeekAgo = LocalDate.now().minusWeeks(2).minusDays(1);
        sellerPosts.forEach(list -> list.forEach(post -> {
            if (post.getDate().toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate().isAfter(twoWeekAgo)) {
                feedPosts.add(post);
            }
        }));
        
        List<PostFeedPostDTO> postFeedPostDTO = feedPosts.stream().map(Post::toPostFeedPostDTO).collect(Collectors.toList());

        postFeedPostDTO.sort(Comparator.comparing(PostFeedPostDTO::getDate));

        if(order.isPresent()) {
            if(order.get().equals("date_desc")) {
                postFeedPostDTO.sort(Comparator.comparing(PostFeedPostDTO::getDate).reversed());
            }
        }

        postFeedDto.setPosts(postFeedPostDTO);
        return postFeedDto;
    }



    @Override
    public void createTestSet() {
//        Post post1
    }

}
