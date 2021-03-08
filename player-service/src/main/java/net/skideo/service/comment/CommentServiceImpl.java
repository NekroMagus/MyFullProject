package net.skideo.service.comment;

import lombok.RequiredArgsConstructor;
import net.skideo.exception.NotFoundException;
import net.skideo.model.Comment;
import net.skideo.model.User;
import net.skideo.model.Video;
import net.skideo.repository.CommentRepository;
import net.skideo.service.user.UserService;
import net.skideo.service.video.VideoService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final UserService userService;
    private final VideoService videoService;
    private final CommentRepository repository;

    @Override
    @Transactional
    public void addComment(long videoId, String text) {
        User currentUser = userService.getCurrentUser();
        Comment comment = new Comment(text, currentUser.getInfo(),false);

        Video video = videoService.findById(videoId);
        video.getComments().add(comment);

        videoService.updateComments(video);
    }

    @Override
    @Transactional
    public void addInnerComment(long commentId, String text) {
        Comment comment = findById(commentId);
        if(comment.isInnerComment()) {
            throw new IllegalArgumentException("This comment is inner");
        }

        User currentUser = userService.getCurrentUser();
        Comment newComment = new Comment(text,currentUser.getInfo(),true);

        comment.getInnerComments().add(newComment);

        updateInnerComments(comment);
    }

    private void updateInnerComments(Comment comment) {
        Comment dbComment = findById(comment.getId());

        dbComment.setInnerComments(comment.getInnerComments());

        repository.save(dbComment);
    }

    private Comment findById(long id) {
        return repository.findById(id).orElseThrow(
                () -> new NotFoundException("Comment not found")
        );
    }

}
