package com.dabblelog.side.domain.dto;


import com.dabblelog.side.domain.Post;
import lombok.Getter;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@Getter
public class SavesDTO {

    private final String title;

    private final String preview;

    private final String lastUpdateDate;

    public SavesDTO(Post post) {
        this.title = post.getTitle();

        this.preview = getPreview(post.getContent());

        this.lastUpdateDate = post.getCreatedDay().format(DateTimeFormatter.ofPattern("yy-MM-dd"));
    }


    static String getPreview(String postContent) {
        List<String> splitPostContentWithTagStart = Arrays.stream(postContent.split("<")).filter(s -> !s.isEmpty()).toList();




        for(String splitContent : splitPostContentWithTagStart) {
            if(splitContent.contains("img>")) {
                continue;
            }
            else {
                String[] splitPostContentWithTagEnd = splitContent.split(">");
                return splitPostContentWithTagEnd[1];
            }
        }

        //<이거로 파싱해서 img>가 속해있으면 제끼고 아니면 그거를 또 >로 파싱한 뒤에 1번째 거 갖고오면 댐 끝까지 이미지뿐이면 빈 문자열 반환


        return "";

    }
}
