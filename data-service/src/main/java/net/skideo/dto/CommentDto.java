package net.skideo.dto;

import lombok.Data;
import net.skideo.model.Comment;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class CommentDto {

    private long id;
    private String text;
    private UserNSDto user;
    private List<LikeDto> likes;
    private List<CommentDto> innerComments;
    private String dateCreated;

    public CommentDto(Comment comment) {
        this.id=comment.getId();
        this.text=comment.getText();
        this.user=new UserNSDto(comment.getUser());
        this.likes = comment.getLikes()
                .stream()
                .map(LikeDto::new)
                .collect(Collectors.toList());
        this.innerComments = comment.getComments()
                .stream()
                .map(CommentDto::new)
                .collect(Collectors.toList());
        this.dateCreated = OffsetDateTime.of(comment.getCreated(), ZoneOffset.UTC).format(DateTimeFormatter.ofPattern("dd-MM-yyyy'T'HH:mm:ss.SSSxxx"));
    }

}
