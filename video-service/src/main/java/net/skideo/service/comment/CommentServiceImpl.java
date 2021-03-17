package net.skideo.service.comment;

import com.nimbusds.jose.proc.SecurityContext;
import lombok.RequiredArgsConstructor;
import net.skideo.exception.NotFoundException;
import net.skideo.model.*;
import net.skideo.repository.AuthRepository;
import net.skideo.repository.CommentRepository;
import net.skideo.repository.InfoRepository;
import net.skideo.service.info.InfoService;
import net.skideo.service.video.VideoService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final InfoService infoService;
    private final VideoService videoService;
    private final CommentRepository repository;

    Logger log = Logger.getLogger(CommentServiceImpl.class.getName());

    @Override
    public void addComment(long videoId, String text) {
        Info currentInfo = infoService.getCurrentInfo();
        Comment comment = new Comment(text, currentInfo,false);

        Video video = videoService.findById(videoId);
        video.getComments().add(comment);

        videoService.save(video);
    }

    @Override
    @Transactional
    public void addInnerComment(long commentId, String text) {
        Comment comment = findById(commentId);
        if(comment.isInnerComment()) {
            throw new IllegalArgumentException("This comment is inner");
        }

        Info currentInfo = infoService.getCurrentInfo();
        Comment newComment = new Comment(text,currentInfo,true);

        comment.getInnerComments().add(newComment);

        repository.save(comment);
    }

    @Override
    public void updateComment(long commentId, String text) {
        Comment dbComment = findById(commentId);
        Info currentInfo = infoService.getCurrentInfo();

        if(dbComment.getInfo().equals(currentInfo)) {
            dbComment.setText(text);
        }

        repository.save(dbComment);
    }

    private Comment findById(long id) {
        return repository.findById(id).orElseThrow(
                () -> new NotFoundException("Comment not found")
        );
    }

}

