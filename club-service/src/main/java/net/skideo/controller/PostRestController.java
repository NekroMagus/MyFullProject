package net.skideo.controller;

import lombok.RequiredArgsConstructor;
import net.skideo.dto.PostDto;
import net.skideo.model.Club;
import net.skideo.model.Post;
import net.skideo.service.club.ClubService;
import net.skideo.service.post.PostService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostRestController {

     private final PostService postService;
     private final ClubService clubService;

     @PostMapping("/post")
     public void savePost(@RequestBody PostDto post) {
         Club currentClub = clubService.getCurrentClub();
         postService.save(new Post(currentClub,post.getCountry(),post.getRoleFootball(),post.isAgent(),
                                   post.getRolePeople(),post.getLeadingLeg(),post.getBirthDate(),post.getVideoLink()));
     }

     @GetMapping("/posts")
     public Page<PostDto> getMyPosts(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "50") int size) {
         return postService.getMyPosts(page,size);
     }

}
