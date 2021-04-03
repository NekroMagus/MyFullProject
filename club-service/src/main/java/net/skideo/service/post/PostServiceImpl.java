package net.skideo.service.post;


import lombok.RequiredArgsConstructor;
import net.skideo.dto.PostDto;
import net.skideo.dto.projections.IdProjection;
import net.skideo.repository.PostRepository;
import net.skideo.exception.PostNotFoundException;
import net.skideo.model.Post;
import net.skideo.service.club.ClubService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final ClubService clubService;

    @Override
    public void save(Post post) {
        postRepository.save(post);
    }

    @Override
    public Post findById(long id) {
        return postRepository.findById(id).orElseThrow(
                () -> new PostNotFoundException("Post not found")
        );
    }

    @Override
    public Page<PostDto> getMyPosts(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        final String LOGIN_CURRENT_CLUB = clubService.getLoginCurrentClub();
        final long ID_CURRENT_CLUB = clubService.getIdByLogin(LOGIN_CURRENT_CLUB);
        return postRepository.findAllByClubId(ID_CURRENT_CLUB,pageable);
    }
}
