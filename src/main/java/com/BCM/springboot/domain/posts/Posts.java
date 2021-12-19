package com.BCM.springboot.domain.posts;

import com.BCM.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class Posts extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String company;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String name;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String position;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String phone_number;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String memo;

    @Builder
    public Posts(String company, String name, String position, String phone_number, String memo){
        this.company = company;
        this.name = name;
        this.position = position;
        this.phone_number = phone_number;
        this.memo = memo;
    }

    public void update(String company, String name, String position, String phone_number, String memo){
        this.company = company;
        this.name = name;
        this.position = position;
        this.phone_number = phone_number;
        this.memo = memo;
    }

}

