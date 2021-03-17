package net.skideo.dto;

import net.skideo.model.Video;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.*;
import java.util.stream.Collectors;


@Data
@NoArgsConstructor
public class VideoDto {

    private long id;
    private String videoLink;
    private String description;
    private UserShortInfoDto author;
    private float rating;
    private Set<LikeDto> likes = new LinkedHashSet<>();
    private List<CommentDto> comments = new LinkedList<>();

    public VideoDto(Video video) {
        this.id = video.getId();
        this.videoLink = video.getVideoLink();
        this.description = video.getDescription();
        this.rating = video.getRating();
        this.author = new UserShortInfoDto(video.getInfo());
        this.likes = video.getLikes()
                .stream()
                .map(LikeDto::new)
                .collect(Collectors.toSet());
        this.comments = video.getComments()
                .stream()
                .map(CommentDto::new)
                .collect(Collectors.toList());
    }

}
