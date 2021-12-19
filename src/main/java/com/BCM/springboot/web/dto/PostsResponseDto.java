package com.BCM.springboot.web.dto;

import com.BCM.springboot.domain.posts.Posts;
import lombok.Getter;

@Getter
public class PostsResponseDto {
    private Long id;
    private String company;
    private String name;
    private String position;
    private String phone_number;
    private String memo;

    public PostsResponseDto(Posts entity){
        this.id = entity.getId();
        this.company = entity.getCompany();
        this.name = entity.getName();
        this.position = entity.getPosition();
        this.phone_number = entity.getPhone_number();
        this.memo = entity.getMemo();
    }
}

