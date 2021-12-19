package com.BCM.springboot.domain.web;

import com.BCM.springboot.domain.posts.Posts;
import com.BCM.springboot.domain.posts.PostsRepository;
import com.BCM.springboot.web.dto.PostsSaveRequestDto;
import com.BCM.springboot.web.dto.PostsUpdateRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsApiControllerTest {
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private PostsRepository postsRepository;
    @After
    public void tearDown() throws Exception{
        postsRepository.deleteAll();
    }
    @Test
    public void Posts_등록된다() throws Exception{
        String company = "회사명";
        String name = "이름";
        String position = "직함";
        String phone_number = "전화번호";
        String memo = "메모";

        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
                .company(company)
                .name(name)
                .position(position)
                .phone_number(phone_number)
                .memo(memo)
                .build();

        String url = "http://localhost:" + port + "/api/v1/posts";

        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getCompany()).isEqualTo(company);
        assertThat(all.get(0).getName()).isEqualTo(name);
        assertThat(all.get(0).getPosition()).isEqualTo(position);
        assertThat(all.get(0).getPhone_number()).isEqualTo(phone_number);
        assertThat(all.get(0).getMemo()).isEqualTo(memo);

    }
    @Test
    public void Posts_수정된다() throws Exception{
        Posts savedPosts = postsRepository.save(Posts.builder()
                .company("회사2")
                .name("이름2")
                .position("직함2")
                .phone_number("전화번호2")
                .memo("메모2")
                .build());

        Long updateId = savedPosts.getId();
        String expected_company = "회사명2";
        String expected_name = "이름2";
        String expected_position = "직함2";
        String expected_phone_number = "전화번호2";
        String expected_Memo = "메모2";

        PostsUpdateRequestDto requestDto = PostsUpdateRequestDto.builder()
                .company(expected_company)
                .name(expected_name)
                .position(expected_position)
                .phone_number(expected_phone_number)
                .memo(expected_Memo)
                .build();

        String url = "http://localhost:" + port + "/api/v1/posts/" + updateId;

        HttpEntity<PostsUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getCompany()).isEqualTo(expected_company);
        assertThat(all.get(0).getName()).isEqualTo(expected_name);
        assertThat(all.get(0).getPosition()).isEqualTo(expected_position);
        assertThat(all.get(0).getPhone_number()).isEqualTo(expected_phone_number);
        assertThat(all.get(0).getMemo()).isEqualTo(expected_Memo);
    }
}