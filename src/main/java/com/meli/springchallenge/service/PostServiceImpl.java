package com.meli.springchallenge.service;

import com.meli.springchallenge.converter.DTOConverter;
import com.meli.springchallenge.dto.NewPostDTO;
import com.meli.springchallenge.dto.PostFeedDTO;
import com.meli.springchallenge.exception.user.UserNotFoundException;
import com.meli.springchallenge.model.Post;
import com.meli.springchallenge.model.User;
import com.meli.springchallenge.model.UserBasic;
import com.meli.springchallenge.repository.PostRepository;
import com.meli.springchallenge.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    public PostFeedDTO getUserPostsByUserId(Integer userId) {
        PostFeedDTO postFeedDto = new PostFeedDTO();
        postFeedDto.setUserId(userId);

        User buyer = userRepository.findUserByUserId(userId);
        Set<UserBasic> followed = buyer.getFollowedSet();
        List<Integer> sellersIds = new ArrayList<>();
        followed.forEach(seller -> sellersIds.add(seller.getUserId()));
        List<Set<Post>> sellerPosts = postRepository.findPostsByUserIdBulk(sellersIds);

//        sellerPosts.stream().forEach(list -> list.forEach(post ->
 //       }));

        return postFeedDto;
    }
}
