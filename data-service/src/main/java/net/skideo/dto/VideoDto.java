package net.skideo.dto;

import net.skideo.model.Video;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
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
    private String dateCreated;

    public VideoDto(Video video) {
        this.id = video.getId();
        this.videoLink = video.getVideoLink();
        this.description = video.getDescription();
        this.rating = video.getRating();
        this.author = new UserShortInfoDto(video.getUser());
        this.likes = video.getLikes()
                .stream()
                .map(LikeDto::new)
                .collect(Collectors.toSet());
        this.comments = video.getComments()
                .stream()
                .map(CommentDto::new)
                .collect(Collectors.toList());
        this.dateCreated = OffsetDateTime.of(video.getCreated(), ZoneOffset.UTC).format(DateTimeFormatter.ofPattern("dd-MM-yyyy'T'HH:mm:ss.SSSxxx"));
    }

}
