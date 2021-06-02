package com.meli.springchallenge.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.meli.springchallenge.exception.user.UserNotFoundException;
import com.meli.springchallenge.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final Map<Integer,User> userDatabase;
    private final String path = "src/main/resources/jsons/user.json";

    public UserRepositoryImpl() {
        this.userDatabase = loadJson();
    }

    @Override
    public User findUserByUserId(Integer userId) {
        User user = userDatabase.get(userId);

        if( user == null) {
            throw new UserNotFoundException();
        }

        return user;
    }

    @Override
    public void save(User userToSave) {
        userDatabase.put(userToSave.getUserId(), userToSave);
        write();
    }

    private Map<Integer,User> loadJson() {

        Map<Integer,User> userMap = null;

        try {
            File file = ResourceUtils.getFile(path);

            ObjectMapper objectMapper = new ObjectMapper();
            TypeReference<Map<Integer,User>> typeRef = new TypeReference<>() {};

            userMap = objectMapper.readValue(file, typeRef);

        } catch (MismatchedInputException e) {
            return new HashMap<Integer, User>();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userMap;
    }
    public void write() {
        String jsonString;

        try {
            ObjectMapper mapper = new ObjectMapper();
            jsonString = mapper.writeValueAsString(this.userDatabase);
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
