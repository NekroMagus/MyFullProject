package net.skideo.service.comment;

import net.skideo.dto.RatingDto;
import net.skideo.model.enums.Rating;

public interface CommentService {

    void addComment(long videoId,String text);

    void addInnerComment(long commentId,String text);

    void updateComment(long commentId,String text);

    void estimateComment(RatingDto ratingDto);

}
