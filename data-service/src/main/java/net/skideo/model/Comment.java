package net.skideo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.skideo.model.abstracts.AbstractComponentEntity;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity
@Table(name = "skideo_comment")
public class Comment extends AbstractComponentEntity {

    private String text;
    private float rating;


    private boolean isInnerComment;

    public Comment(String text,Info info,boolean isInnerComment) {
        this.text=text;
        setInfo(info);
        this.isInnerComment=isInnerComment;
    }

}
