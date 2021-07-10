package com.example.springboot.service;

import com.example.springboot.domain.posts.Posts;
import com.example.springboot.domain.posts.PostsRepository;
import com.example.springboot.web.dto.PostsListResponseDto;
import com.example.springboot.web.dto.PostsResponseDto;
import com.example.springboot.web.dto.PostsSaveRequestDto;
import com.example.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor    //When using constructor injection, this annotation is convenient.
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("There is no such post. id= " + id));
        return new PostsResponseDto(entity);
    }

    //save
    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    //update
    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("There is no such post. id= " + id));
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    //이 옵션은 트랜잭션 범위는 유지하되, 조회기능만 남겨두어 조회속도가 개선되므로 등록,수정,삭제 기능이 전혀 없는 메서드에서 아용하는 것을 추천한다.
    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc(){
        return postsRepository.findAllDesc().stream().map(PostsListResponseDto::new).collect(Collectors.toList());
    }

    /*
    * JpaRepository에서 delete 메서드를 지원하고 있으므로 이를 활용한다.
    * 엔티티를 파라미터로 받아 삭제할 수도 있고, deleteById메서드를 이용하여 id로 삭제할 수도 있다.
    * 존재하는 Posts인지 확인한 뒤 엔티티 조회하여 그대로 삭제한다.
    * */
    //delete
    @Transactional
    public void delete(Long id){
        Posts posts = postsRepository.findById(id).orElseThrow(()->new IllegalArgumentException("There is no such post. id=" +id));
        postsRepository.delete(posts);
    }
}
