package net.skideo.service.comment;

public interface CommentService {

    void addComment(long videoId,String text);

    void addInnerComment(long commentId,String text);

}
