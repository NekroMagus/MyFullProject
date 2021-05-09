package net.skideo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.skideo.dto.base.Dto;
import net.skideo.model.Post;
import net.skideo.model.enums.LeadingLeg;
import net.skideo.model.enums.RoleFootball;
import net.skideo.model.enums.RolePeople;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDto extends Dto {

    private String country;
    private RoleFootball roleFootball;
    private boolean agent;
    private RolePeople rolePeople;
    private LeadingLeg leadingLeg;
    private LocalDate birthDate;
    private String videoLink;
    private String dateCreated;

    public PostDto(Post post) {
        this.country=post.getCountry();
        this.roleFootball=post.getRoleFootball();
        this.agent=post.getAgent();
        this.rolePeople=post.getRolePeople();
        this.leadingLeg=post.getLeadingLeg();
        this.birthDate=post.getBirthDate();
        this.videoLink=post.getVideoLink();
        this.dateCreated = OffsetDateTime.of(post.getCreated(), ZoneOffset.UTC).format(DateTimeFormatter.ofPattern("dd-MM-yyyy'T'HH:mm:ss.SSSxxx"));
    }

}
