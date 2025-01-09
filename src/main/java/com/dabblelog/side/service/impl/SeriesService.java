package com.dabblelog.side.service.impl;

import com.dabblelog.side.domain.Blog;
import com.dabblelog.side.domain.Series;
import com.dabblelog.side.domain.dto.getSeriesDTO;
import com.dabblelog.side.repository.PostRepository;
import com.dabblelog.side.repository.SeriesRepository;
import com.dabblelog.side.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SeriesService {

    @Autowired
    BlogService blogService;


    @Autowired
    PostRepository postRepository;

    @Autowired
    SeriesRepository seriesRepository;

    @Autowired
    UserRepository userRepository;

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

    //시리즈 삭제 메서드

    @Transactional
    public void deleteSeries(Series series) {
        seriesRepository.delete(series);
    }

    public Page<getSeriesDTO> getSeries(String email, Pageable pageable) {

         Blog blog = blogService.ifBlogIsNotExistCreateBlog(email);

        return  seriesRepository.findAllByBlogId(blog,pageable).map(s -> new getSeriesDTO(s,postRepository.countByBlogIdAndSeriesId(blog,s),
                getThumbnails(postRepository.findTop1ByBlogIdAndSeriesIdOrderByCreatedDayDesc(blog,s).getContent()),
                postRepository.findTop1ByBlogIdAndSeriesIdOrderByCreatedDayDesc(blog,s).getCreatedDay()));


    }


    static String getThumbnails(String postContent) {
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
