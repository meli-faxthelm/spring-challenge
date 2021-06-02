package com.meli.springchallenge.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.meli.springchallenge.exception.post.PostIdAlreadyExistsException;
import com.meli.springchallenge.model.Post;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

@Repository
public class PostRepositoryImpl implements PostRepository{

    private final Map<Integer, Set<Post>> postDatabase;
    private final String path = "src/main/resources/jsons/post.json";

    public PostRepositoryImpl(){
        postDatabase = loadJson();
    }

    @Override
    public void save(Post postToSave) {
        Set<Post> postSet = this.findPostsByUserId(postToSave.getUserId());
        boolean hasSaved = postSet.add(postToSave);
        if (hasSaved){
            postDatabase.put(postToSave.getUserId(), postSet);
            write();
        }
        else {
            throw new PostIdAlreadyExistsException();
        }

    }

    @Override
    public Set<Post> findPostsByUserId(Integer userId) {
        Set<Post> postSet = postDatabase.get(userId);
        if (postSet == null) {
            postSet = new HashSet<Post>();
        }

        return postSet;
    }

    @Override
    public List<Set<Post>> findPostsByUserIdBulk(List<Integer> userIdList) {
        List<Set<Post>> returnList = new ArrayList<Set<Post>>();
        userIdList.forEach(userId -> returnList.add(postDatabase.get(userId)));
        return returnList;
    }

    private Map<Integer, Set<Post>> loadJson() {

        Map<Integer,Set<Post>> userMap = null;

        try {
            File file = ResourceUtils.getFile(path);

            ObjectMapper objectMapper = new ObjectMapper();
            TypeReference<Map<Integer,Set<Post>>> typeRef = new TypeReference<>() {};

            userMap = objectMapper.readValue(file, typeRef);

        } catch (MismatchedInputException e) {
            return new HashMap<Integer,Set<Post>>();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userMap;
    }

    public void write() {
        String jsonString;

        try {
            ObjectMapper mapper = new ObjectMapper();
            jsonString = mapper.writeValueAsString(this.postDatabase);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return;
        }

        try (FileWriter file = new FileWriter(path)) {
            file.write(jsonString);
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
