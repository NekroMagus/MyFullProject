package net.skideo.controller;

import lombok.RequiredArgsConstructor;
import net.skideo.dto.CommentADto;
import net.skideo.service.comment.CommentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/comment")
public class CommentRestController {

    private final CommentService commentService;

    @PostMapping
    public void addComment(@Valid @RequestBody CommentADto commentDto) {
        commentService.addComment(commentDto.getId(),commentDto.getText());
    }

    @PostMapping("/inner")
    public void addInnerComment(@Valid @RequestBody CommentADto commentDto) {
        commentService.addInnerComment(commentDto.getId(),commentDto.getText());
    }

}
