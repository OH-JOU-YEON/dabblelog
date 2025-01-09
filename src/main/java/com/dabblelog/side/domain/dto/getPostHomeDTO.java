package com.dabblelog.side.domain.dto;

import com.dabblelog.side.domain.Post;
import lombok.Getter;

import java.time.format.DateTimeFormatter;

@Getter
public class getPostHomeDTO {

    private final String title;

    private final String nickname;


    //미리보기
    private final String preview;

    private final String createdDay;

    private final Long totalRepleCount;

    private final int likeCount;

    private final String thumbnail;


    public getPostHomeDTO(Post post, Long totalRepleCount) {

        this.title = post.getTitle();

        this.preview = getPreview(post.getContent());

        this.createdDay = post.getCreatedDay().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        this.likeCount = post.getLikeCount();

        this.thumbnail = getThumbnails(post.getContent());

        this.totalRepleCount = totalRepleCount;

        this.nickname = post.getBlogId().getUser().getName();


    }

    static String getPreview(String postContent) {
       String[] splitPostContentWithTagStart = postContent.split("<");



       for(String splitContent : splitPostContentWithTagStart) {
           if(splitContent.contains("img>")) {
               continue;
           }
           else {
               String[] splitPostContentWithTagEnd = splitContent.split(">");
               return splitPostContentWithTagEnd[0];
           }
       }

       //<이거로 파싱해서 img>가 속해있으면 제끼고 아니면 그거를 또 >로 파싱한 뒤에 1번째 거 갖고오면 댐 끝까지 이미지뿐이면 빈 문자열 반환


        return "";

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
