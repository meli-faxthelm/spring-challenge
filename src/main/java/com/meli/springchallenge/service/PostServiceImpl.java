package com.meli.springchallenge.service;

import com.meli.springchallenge.converter.DTOConverter;
import com.meli.springchallenge.dto.*;
import com.meli.springchallenge.exception.post.PostPromoDiscountTooBigException;
import com.meli.springchallenge.exception.user.UserIsNotSellerException;
import com.meli.springchallenge.exception.user.UserNotFoundException;
import com.meli.springchallenge.model.*;
import com.meli.springchallenge.repository.PostRepository;
import com.meli.springchallenge.repository.PromoPostRepository;
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
    @Autowired
    PromoPostRepository promoPostRepository;

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
    public void createPromoPost(NewPromoPostDTO newPromoPostDTO) {
        User seller = userRepository.findUserByUserId(newPromoPostDTO.getUserId());
        if (seller != null && seller.isSeller()) {
            if (newPromoPostDTO.getDiscount() > 0.99) {
                throw new PostPromoDiscountTooBigException();
            }
            postRepository.save(DTOConverter.newPromoPostDTOToPost(newPromoPostDTO));
            promoPostRepository.save(DTOConverter.newPromoPostDTOToPromoPostDetail(newPromoPostDTO));
        }
        else{
            throw new UserNotFoundException();
        }

    }

    @Override
    public PromoCountDTO promoCountByUserId(Integer userId) {
        PromoCountDTO promoCountDTO = new PromoCountDTO();
        User seller = userRepository.findUserByUserId(userId);
        if (!seller.isSeller()) {
           throw new UserIsNotSellerException();
        }
        Set<PromoPostDetail> promoSet = promoPostRepository.findPromoPostDetailsByUserId(userId);

        promoCountDTO.setUserId(userId);
        promoCountDTO.setUserName(seller.getUserName());
        promoCountDTO.setPromoproducts_count(promoSet.size());


        return promoCountDTO;
    }

    @Override
    public PromoPostListDTO promoList(Integer userId) {
        PromoPostListDTO promoPostListDTO = new PromoPostListDTO();
        User seller = userRepository.findUserByUserId(userId);
        Set<Post> sellerPosts = postRepository.findPostsByUserId(userId);
        Set<PromoPostDetail> promosDetails = promoPostRepository.findPromoPostDetailsByUserId(userId);
        List<PromoPostListItemDTO> promoPostList = promosDetails.stream().map(detail -> {
            PromoPostListItemDTO item = new PromoPostListItemDTO();
            for(Post post : sellerPosts) {
                if(post.getId_post() == detail.getId_post()) {
                    item.setId_post(detail.getId_post());
                    item.setCategory(post.getCategory());
                    item.setDate(post.getDate());
                    item.setPrice(post.getPrice());
                    item.setDetail(post.getDetail().toProductDTO());
                    item.setHasPromo(detail.isHasPromo());
                    item.setDiscount(detail.getDiscount());
                    break;
                }
            }
            return item;
        }).collect(Collectors.toList());

        promoPostListDTO.setUserId(userId);
        promoPostListDTO.setUserName(seller.getUserName());
        promoPostListDTO.setPosts(promoPostList);

        return promoPostListDTO;
    }


    @Override
    public void createTestSet() {
        Date date1 = Date.from(LocalDate.now().minusWeeks(1).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        Date date2 = Date.from(LocalDate.now().minusWeeks(2).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        Date date3 = Date.from(LocalDate.now().minusWeeks(3).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        Product product1 = new Product(1,"Cadeira","Balanço","Da vovó", "Marrom", "Usada");
        Product product2 = new Product(2,"Cadeira Razer","Gamer","Razer", "Verde", "Novo modelo");
        Product product3 = new Product(3,"Teclado pequeno","Produtividade","Abelinha", "Cinza", "Pequeno");


        Post post1 = new Post(6,1,date1, product1,10,1400.00);
        Post post2 = new Post(6,2,date1, product2,10,2000.00);
        Post post3 = new Post(7,3,date2, product2,20,1450.00);
        Post post4 = new Post(8,4,date3, product3,20,450.00);
        postRepository.save(post1);
        postRepository.save(post2);
        postRepository.save(post3);
        postRepository.save(post4);
    }

}
