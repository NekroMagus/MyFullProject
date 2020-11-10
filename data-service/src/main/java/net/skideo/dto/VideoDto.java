package net.skideo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.skideo.model.Video;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


@Data
@NoArgsConstructor
public class VideoDto {

    private long id;
    private String videoLink;
    private UserShortInfoDto author;
    private Set<LikeDto> likes = new HashSet<>();

    public VideoDto(Video video) {
        this.id = video.getId();
        this.videoLink = video.getVideoLink();
        this.author = new UserShortInfoDto(video.getUser());
        this.likes = video.getLikes()
                .stream()
                .map(LikeDto::new)
                .collect(Collectors.toSet());
    }

}
