package com.BCM.springboot.web.dto;

import com.BCM.springboot.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {
    private String company;
    private String name;
    private String position;
    private String phone_number;
    private String memo;
    @Builder
    public PostsSaveRequestDto(String company, String name, String position, String phone_number, String memo) {
        this.company = company;
        this.name = name;
        this.position = position;
        this.phone_number = phone_number;
        this.memo = memo;
    }

    public Posts toEntity(){
        return Posts.builder()
                .company(company)
                .name(name)
                .position(position)
                .phone_number(phone_number)
                .memo(memo)
                .build();
    }
}
