package com.example.springboot.web.dto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class HelloResponseDtoTest {

    @Test
    void lombok_test() {
        //given: 만들어둔 두 변수에 임의의 값을 넣는다.
        String name = "name";
        int amount = 1000;

        //when: 롬복으로 만들어둔 생성자에 두 값을 넣는다.
        HelloResponseDto dto = new HelloResponseDto(name, amount);

        //then: asserThat을 활용하여 dto의 name과 amount가 각각 name과 1000이 맞는지 확인한다.
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);
    }
}