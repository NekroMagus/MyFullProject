package net.skideo.model.abstracts;

import lombok.Data;
import net.skideo.model.Comment;
import net.skideo.model.User;
import net.skideo.model.Like;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Data
@MappedSuperclass
public abstract class AbstractComponentEntity extends BaseEntity {

    @ManyToOne
    private User user;

    @OneToMany(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE})
    private List<Like> likes=new LinkedList<>();

    @OneToMany(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE})
    private List<Comment> comments = new LinkedList<>();

}
