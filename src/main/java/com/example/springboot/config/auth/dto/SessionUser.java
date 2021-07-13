package com.example.springboot.config.auth.dto;

import com.example.springboot.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

/*
* SessionUser에는 인증된 사용자 정보만 필요하므로 name, email, picture만 필드로 선언한다.
* User entity class에 serializable를 추가하는 대신 SessionUser를 새로 만든 이유는 entity class는 언제 다른 entities와 관계가 형성될지 모르기 때문이다.
* 따라서 serializable 기능을 가진 Session DTO를 하나 추가로 만드는 것이 이후의 운영과 유지보수에 많은 도움이 된다.
* */
@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
