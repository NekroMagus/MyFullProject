package net.skideo.dto;

import net.skideo.model.Comment;
import net.skideo.model.Video;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Data
@NoArgsConstructor
public class VideoDto {

    private long id;
    private String videoLink;
    private String description;
    private UserShortInfoDto author;
    private Set<LikeDto> likes = new HashSet<>();
    private List<CommentDto> comments = new LinkedList<>();

    public VideoDto(Video video) {
        this.id = video.getId();
        this.videoLink = video.getVideoLink();
        this.description = video.getDescription();
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
