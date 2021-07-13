package com.example.springboot.web;

import com.example.springboot.config.auth.dto.SessionUser;
import com.example.springboot.service.PostsService;
import com.example.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {
    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model){   //Model: 서버템플릿 엔진에서 사용할 수 있는 객체를 저장할 수 있다.
        model.addAttribute("posts", postsService.findAllDesc());
        // 앞서 작성된 CustomOAuth2UserService에서 로그인 성공시 세션에 SessionUser를 저장하도록 구성했다.
        // 즉, 로그인 성공시 httpSession.getAttribute("user")로 값을 가져올 수 있다.
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if(user!=null){ //세션에 저장된 값이 있을때만 model에 userName으로 등록한다. 아무런 값이 없으면 로그인 버튼이 출력된다.
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }

    //Save
    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }

    //Update
    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);
        return "posts-update";
    }

}
