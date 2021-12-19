package com.BCM.springboot.service.posts;

import com.BCM.springboot.domain.posts.Posts;
import com.BCM.springboot.domain.posts.PostsRepository;
import com.BCM.springboot.web.dto.PostsListResponseDto;
import com.BCM.springboot.web.dto.PostsResponseDto;
import com.BCM.springboot.web.dto.PostsSaveRequestDto;
import com.BCM.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 명함이 없습니다. id=" + id));
        posts.update(requestDto.getCompany(), requestDto.getName(), requestDto.getPosition(), requestDto.getPhone_number(), requestDto.getMemo());
        return id;
    }
    public PostsResponseDto findById(Long id){
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 명함이 없습니다. id=" + id));
        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly= true)
    public List<PostsListResponseDto> findALLDesc(){
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id){
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 명함이 없습니다. id=" + id));
        postsRepository.delete(posts);
    }
}
