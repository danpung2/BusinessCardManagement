package com.BCM.springboot.domain.posts;

import com.BCM.springboot.domain.posts.Posts;
import com.BCM.springboot.domain.posts.PostsRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After
    public void cleanup(){
        postsRepository.deleteAll();
    }

    @Test
    public void 명함_불러오기(){
        String company = "회사명";
        String name = "이름";
        String position = "직함";
        String phone_number = "전화번호";
        String memo = "메모";

        postsRepository.save(Posts.builder()
                .company(company)
                .name(name)
                .position(position)
                .phone_number(phone_number)
                .memo(memo)
                .build());

        List<Posts> postsList = postsRepository.findAll();

        Posts posts = postsList.get(0);
        assertThat(posts.getCompany()).isEqualTo(company);
        assertThat(posts.getName()).isEqualTo(name);
        assertThat(posts.getPosition()).isEqualTo(position);
        assertThat(posts.getPhone_number()).isEqualTo(phone_number);
        assertThat(posts.getMemo()).isEqualTo(memo);
    }


}