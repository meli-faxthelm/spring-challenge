package com.meli.springchallenge.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.meli.springchallenge.exception.post.PostIdAlreadyExistsException;
import com.meli.springchallenge.model.PromoPostDetail;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

@Repository
public class PromoPostRepositoryImpl implements PromoPostRepository{

    private final Map<Integer, Set<PromoPostDetail>> promoPostDatabase;
    private final String path = "src/main/resources/jsons/promopost.json";

    public PromoPostRepositoryImpl() {
        this.promoPostDatabase = loadJson();
    }


    @Override
    public void save(PromoPostDetail promoPostDetailToSave) {
        Set<PromoPostDetail> promoPostDetailsSet = this.findPromoPostDetailsByUserId(promoPostDetailToSave.getUserId());
        boolean hasSaved = promoPostDetailsSet.add(promoPostDetailToSave);
        if (hasSaved){
            promoPostDatabase.put(promoPostDetailToSave.getUserId(), promoPostDetailsSet);
            write();
        }
        else {
            throw new PostIdAlreadyExistsException();
        }

    }

    @Override
    public Set<PromoPostDetail> findPromoPostDetailsByUserId(Integer userId) {
        Set<PromoPostDetail> promoPostDetailSet = promoPostDatabase.get(userId);
        if (promoPostDetailSet == null) {
            promoPostDetailSet = new HashSet<PromoPostDetail>();
        }

        return promoPostDetailSet;
    }

    private Map<Integer, Set<PromoPostDetail>> loadJson() {

        Map<Integer,Set<PromoPostDetail>> userMap = null;

        try {
            File file = ResourceUtils.getFile(path);

            ObjectMapper objectMapper = new ObjectMapper();
            TypeReference<Map<Integer,Set<PromoPostDetail>>> typeRef = new TypeReference<>() {};

            userMap = objectMapper.readValue(file, typeRef);

        } catch (MismatchedInputException e) {
            return new HashMap<Integer,Set<PromoPostDetail>>();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userMap;
    }

    public void write() {
        String jsonString;

        try {
            ObjectMapper mapper = new ObjectMapper();
            jsonString = mapper.writeValueAsString(this.promoPostDatabase);
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
