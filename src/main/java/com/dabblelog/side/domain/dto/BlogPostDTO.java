package com.dabblelog.side.domain.dto;

import com.dabblelog.side.domain.Post;
import lombok.Getter;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@Getter
public class BlogPostDTO {

    private final String postImg;

    private final String title;

    private final String preview;

    private final String createdDay;

    private final Long likeCount;

    private final String uuid;


    public BlogPostDTO(Post post) {

        this.postImg = getThumbnails(post.getContent());

        this.title = post.getTitle();

        this.uuid = post.getUuid();

        this.preview = getPreview(post.getContent());

        this.createdDay = post.getCreatedDay().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        this.likeCount = (long) post.getLikeCount();


    }

    static String getPreview(String postContent) {
        List<String> splitPostContentWithTagStart = Arrays.stream(postContent.split("<p>")).filter(s -> !s.isEmpty()).toList();




        for(String splitContent : splitPostContentWithTagStart) {
            if(splitContent.contains("<img") || splitContent.contains("<br>")) {
                continue;
            }
            else {
                String[] splitPostContentWithTagEnd = splitContent.split("</p>");
                return splitPostContentWithTagEnd[0];
            }
        }

        //<이거로 파싱해서 img>가 속해있으면 제끼고 아니면 그거를 또 >로 파싱한 뒤에 1번째 거 갖고오면 댐 끝까지 이미지뿐이면 빈 문자열 반환


        return "";

    }

    static String getThumbnails(String postContent) {
        String[] splitPostContentWithTagStart = postContent.split("<p>");

        //위 메서드와 로직 반대. img> 포함하면 공백으로 잘라서 검사 후에 src를 포함하면 =로 자르고 뒤에 거 반환

        for(String splitContent : splitPostContentWithTagStart) {
            if(splitContent.contains("<img")) {

                String[] splitContentWithBlank = splitContent.split(" ");

                for(String splitWithBlankContent : splitContentWithBlank) {
                    if(splitWithBlankContent.contains("src")) {
                        String[] splitContentWithSrc = splitWithBlankContent.split("src=");
                        return splitContentWithSrc[1].replace("\"","");
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
