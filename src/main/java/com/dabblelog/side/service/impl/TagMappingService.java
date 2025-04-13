package com.dabblelog.side.service.impl;


import com.dabblelog.side.domain.Blog;
import com.dabblelog.side.domain.Post;
import com.dabblelog.side.domain.PostTag;
import com.dabblelog.side.domain.TagMapping;
import com.dabblelog.side.repository.PostRepository;
import com.dabblelog.side.repository.TagMappingRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagMappingService {


    TagMappingRepository tagMappingRepository;


    PostRepository postRepository;


    BlogService blogService;

    //태그 매핑 생성하는 로직

    public TagMapping createTagMapping(Post postId, PostTag tagId) {
        return tagMappingRepository.save(new TagMapping(tagId, postId));
    }

    public List<String> getTagTitle(String blogName,String postTitle) {

        Blog blog = blogService.getBlogByName(blogName);
        Post post = postRepository.findByBlogIdAndTitle(blog,postTitle);

        return tagMappingRepository.findAllByPostId(post).stream().map(s->s.getTagId().getTitle()).toList();

    }



    //매핑 삭제하는 로직

    @Transactional
    public void deleteTagMapping(Post postId, PostTag tagId) {
        TagMapping tagMapping = tagMappingRepository.findByPostIdAndTagId(postId, tagId).get();

        tagMappingRepository.delete(tagMapping);



    }
}
