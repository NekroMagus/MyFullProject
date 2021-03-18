package net.skideo.controller;

import lombok.RequiredArgsConstructor;
import net.skideo.dto.CommentADto;
import net.skideo.dto.RatingDto;
import net.skideo.service.comment.CommentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/comment")
public class CommentRestController {

    private final CommentService commentService;

    @PostMapping("/comment")
    public void addComment(@Valid @RequestBody CommentADto commentDto) {
        commentService.addComment(commentDto.getId(),commentDto.getText());
    }

    @PostMapping("/comment/inner")
    public void addInnerComment(@Valid @RequestBody CommentADto commentDto) {
        commentService.addInnerComment(commentDto.getId(),commentDto.getText());
    }

    @PostMapping("/estimate")
    public void estimateComment(@Valid @RequestBody RatingDto ratingDto) {
        commentService.estimateComment(ratingDto);
    }

}
