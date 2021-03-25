package net.skideo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
@Table(name = "skideo_comment")
public class Comment extends AbstractEntity {

    private String text;
    private float rating;

    @ManyToOne
    private Info info;

    @OneToMany(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE})
    private List<Like> likes=new LinkedList<>();

    @ManyToMany(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE})
    private List<Comment> innerComments = new LinkedList<>();

    private boolean isInnerComment;

    public Comment(String text,Info info,boolean isInnerComment) {
        this.text=text;
        this.info=info;
        this.isInnerComment=isInnerComment;
    }

}
