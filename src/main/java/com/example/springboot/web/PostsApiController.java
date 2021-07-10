package com.example.springboot.web;

import com.example.springboot.service.PostsService;
import com.example.springboot.web.dto.PostsResponseDto;
import com.example.springboot.web.dto.PostsSaveRequestDto;
import com.example.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class PostsApiController {

    private final PostsService postsService;

    //Create - POST Method
    @PostMapping("/api/v1/posts")
    @ResponseBody
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

    //Update - PUT Method
    @PutMapping("/api/v1/posts/{id}")
    @ResponseBody
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    //Read - GET Method
    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id) {
        return postsService.findById(id);
    }

    //Delete - DELETE Method
    @DeleteMapping("/api/v1/posts/{id}")
    @ResponseBody
    public Long delete(@PathVariable Long id){
        postsService.delete(id);
        return id;
    }
}
