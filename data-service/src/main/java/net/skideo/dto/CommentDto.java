package net.skideo.dto;

import lombok.Data;
import net.skideo.model.Comment;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class CommentDto {

    private String text;
    private UserNSDto user;
    private List<LikeDto> likes;
    private List<CommentDto> innerComments;

    public CommentDto(Comment comment) {
        this.text=comment.getText();
        this.user=new UserNSDto(comment.getInfo());
        this.likes = comment.getLikes()
                .stream()
                .map(LikeDto::new)
                .collect(Collectors.toList());
        this.innerComments = comment.getInnerComments()
                .stream()
                .map(CommentDto::new)
                .collect(Collectors.toList());
    }

}
