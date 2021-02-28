package net.skideo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.skideo.model.Post;
import net.skideo.model.enums.LeadingLeg;
import net.skideo.model.enums.RoleFootball;
import net.skideo.model.enums.RolePeople;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {

    private String country;
    private RoleFootball roleFootball;
    private boolean agent;
    private RolePeople rolePeople;
    private LeadingLeg leadingLeg;
    private LocalDate birthDate;
    private String videoLink;

    public PostDto(Post post) {
        this.country=post.getCountry();
        this.roleFootball=post.getRoleFootball();
        this.agent=post.isAgent();
        this.rolePeople=post.getRolePeople();
        this.leadingLeg=post.getLeadingLeg();
        this.birthDate=post.getBirthDate();
        this.videoLink=post.getVideoLink();
    }

}
