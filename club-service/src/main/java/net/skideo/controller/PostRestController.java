package net.skideo.controller;

import lombok.RequiredArgsConstructor;
import net.skideo.model.Post;
import net.skideo.service.post.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostRestController {

     private final PostService postService;

     @PostMapping
     public void savePost(@RequestBody Post post) {
         postService.save(post);
     }

}
