package com.dabblelog.side.service.impl;

import com.dabblelog.side.domain.Blog;
import com.dabblelog.side.domain.Post;
import com.dabblelog.side.domain.Series;
import com.dabblelog.side.domain.dto.PostHomeDTO;
import com.dabblelog.side.domain.dto.SeriesDTO;
import com.dabblelog.side.repository.BlogRepository;
import com.dabblelog.side.repository.PostRepository;
import com.dabblelog.side.repository.RepleRepository;
import com.dabblelog.side.repository.SeriesRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SeriesService {


   private final BlogService blogService;



   private final PostRepository postRepository;


   private final SeriesRepository seriesRepository;


   private final BlogRepository blogRepository;

   private final RepleRepository repleRepository;

    //새 시리즈 생성 로직

    @Transactional
    public Series createSeries(Blog blog, String color, String title) {

        if(seriesRepository.findByBlogIdAndTitle(blog,title).isPresent()) {
           return seriesRepository.findByBlogIdAndTitle(blog,title).get();
        }

        return seriesRepository.save(new Series(blog, color, title));
    }

    //시리즈 수정 로직
    //그 전에 수정하려고 하는 값이 null이면 그거 검사해줘야함.

    @Transactional
    public Series updateSeries(String color, String title) {

        Series series = seriesRepository.findByTitle(title).get();

        series.update(color,title);

      return seriesRepository.save(series);

    }


    public Page<PostHomeDTO> getSeriesDetails(String uuid, String blogName,Pageable pageable){

        Blog blog = blogService.getBlogByName(blogName);

        Optional<Series> series = seriesRepository.findByBlogIdAndUuid(blog,uuid);

        return  postRepository.findAllByBlogIdAndSeriesId(blog,series.get(),pageable).map(s -> new PostHomeDTO(s, repleRepository.countByPostId(s)));



    }

    //시리즈 삭제 메서드

    @Transactional
    public void deleteSeries(Series series) {
        seriesRepository.delete(series);
    }



    public Page<SeriesDTO> getSeries(String blogName, Pageable pageable) {

         Blog blog = blogRepository.findByBlogName(blogName).get();

        return seriesRepository.findAllByBlogId(blog, pageable).map(s -> new SeriesDTO(s,postRepository.countByBlogIdAndSeriesId(blog,s),getThumbnails(
                s,blog
        )));


    }
    // 한 시리즈 디테일 내의 시리즈 정보
    public SeriesDTO getSeriesInSeriesDetails(String blogName, String uuid) {

        Blog blog = blogService.getBlogByName(blogName);

        Series series = seriesRepository.findByBlogIdAndUuid(blog,uuid).get();

        return new SeriesDTO(series, postRepository.countByBlogIdAndSeriesId(blog,series),getThumbnails(series,blog) );
    }






    public String getThumbnails(Series series, Blog blog) {

        Pageable pageable = PageRequest.of(8,10);


        List<Post> postList = postRepository.findAllByBlogIdAndSeriesId(blog,series,pageable).stream().toList();

        if(postList.isEmpty()) {
            return "";
        }
        postList.sort(Collections.reverseOrder());
        String postContent = postList.get(0).getContent();
        String[] splitPostContentWithTagStart = postContent.split("<");

        //위 메서드와 로직 반대. img> 포함하면 공백으로 잘라서 검사 후에 src를 포함하면 =로 자르고 뒤에 거 반환

        for(String splitContent : splitPostContentWithTagStart) {
            if(splitContent.contains("img>")) {

                String[] splitContentWithBlank = splitContent.split(" ");

                for(String splitWithBlankContent : splitContentWithBlank) {
                    if(splitWithBlankContent.contains("src")) {
                        String[] splitContentWithSrc = splitWithBlankContent.split("=");
                        return splitContentWithSrc[1];
                    }
                    else {
                        continue;
                    }
                }

            }
            else {
                continue;
            }
        }


        return "";

    }



}
