package net.skideo.model.abstracts;

import lombok.Data;
import net.skideo.model.Comment;
import net.skideo.model.User;
import net.skideo.model.Like;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Data
@MappedSuperclass
public abstract class AbstractComponentEntity extends BaseEntity {

    @ManyToOne
    private User user;

    @OneToMany(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE})
    private Set<Like> likes=new LinkedHashSet<>();

    @OneToMany(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE})
    private Set<Comment> comments = new LinkedHashSet<>();

}
