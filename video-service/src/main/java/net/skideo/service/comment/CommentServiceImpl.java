package net.skideo.service.comment;

import lombok.RequiredArgsConstructor;
import net.skideo.dto.RatingDto;
import net.skideo.exception.AlreadyRatedException;
import net.skideo.exception.NotFoundException;
import net.skideo.model.*;
import net.skideo.repository.CommentRepository;
import net.skideo.service.user.UserService;
import net.skideo.service.video.VideoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final UserService userService;
    private final VideoService videoService;
    private final CommentRepository repository;

    @Override
    public void addComment(long videoId, String text) {
        User currentUser = userService.getCurrentUser();
        Comment comment = new Comment(text, currentUser,false);

        Video video = videoService.findById(videoId);
        video.getComments().add(comment);

        videoService.save(video);
    }

    @Override
    @Transactional
    public void addInnerComment(long commentId, String text) {
        Comment comment = findById(commentId);
        if(comment.getIsInnerComment()) {
            throw new IllegalArgumentException("This comment is inner");
        }

        User currentUser = userService.getCurrentUser();
        Comment newComment = new Comment(text, currentUser,true);

        comment.getComments().add(newComment);

        repository.save(comment);
    }

    @Override
    public void updateComment(long commentId, String text) {
        Comment dbComment = findById(commentId);
        User currentUser = userService.getCurrentUser();

        if(dbComment.getUser().equals(currentUser)) {
            dbComment.setText(text);
        }

        repository.save(dbComment);
    }

    @Override
    public void estimateComment(RatingDto ratingDto) {
        Comment comment = findById(ratingDto.getId());
        User currentUser = userService.getCurrentUser();

        if(isAlreadyLiked(comment, currentUser)) {
            throw new AlreadyRatedException("You already liked this comment");
        }

        Like newLike = new Like(ratingDto.getRating(), currentUser);

        comment.getLikes().add(newLike);

        save(comment);
    }

    private Comment findById(long id) {
        return repository.findById(id).orElseThrow(
                () -> new NotFoundException("Comment not found")
        );
    }

    private void save(Comment comment) {
        repository.save(comment);
    }

    private boolean isAlreadyLiked(Comment comment, User currentUser) {
        for(Like like : comment.getLikes()) {
            if(like.getUser().equals(currentUser)) {
                return true;
            }
        }
        return false;
    }

}

