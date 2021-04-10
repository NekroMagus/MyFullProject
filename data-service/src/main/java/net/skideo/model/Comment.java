package net.skideo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.skideo.model.abstracts.AbstractComponentEntity;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity
@Table(name = "comment")
public class Comment extends AbstractComponentEntity {

    private String text;

    private boolean isInnerComment;

    public Comment(String text, User user, boolean isInnerComment) {
        this.text=text;
        setUser(user);
        this.isInnerComment=isInnerComment;
    }

}
