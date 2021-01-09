package net.skideo.controller;

import lombok.RequiredArgsConstructor;
import net.skideo.dto.PostDto;
import net.skideo.model.Post;
import net.skideo.service.post.PostService;
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
     public void savePost(@RequestBody PostDto post) {
         postService.save(new Post(post.getCountry(),post.getRoleFootball(),post.isAgent(),
                                   post.getRolePeople(),post.getLeadingLeg(),post.getBirthDate(),post.getVideoLink()));
     }

}
