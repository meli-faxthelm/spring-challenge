package com.meli.springchallenge.repository;

import com.meli.springchallenge.model.Post;

import java.util.List;
import java.util.Set;

public interface PostRepository {

    void save(Post postToSave);
    Set<Post> findPostsByUserId(Integer userId);
    List<Set<Post>> findPostsByUserIdBulk(List<Integer> userIdList);


}
