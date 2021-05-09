package net.skideo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.skideo.dto.base.Dto;
import net.skideo.model.User;
import net.skideo.model.enums.LeadingLeg;
import net.skideo.model.enums.RoleFootball;
import net.skideo.model.enums.RolePeople;

@Data
@NoArgsConstructor
public class AdminPlayerInfoDto extends Dto {

    private long id;
    private String name;
    private String surname;
    private String email;
    private String cityTitle;
    private String countryTitle;
    private String phone;
    private LeadingLeg leadingLeg;
    private RoleFootball roleFootball;
    private RolePeople rolePeople;
    private AdminClubInfoDto club;
    private boolean hasAgent;
    private String birthDate;
    private String linkSocialNetwork;

    public AdminPlayerInfoDto(User user) {
        setId(user.getId());
        setName(user.getName());
        setSurname(user.getSurname());
        setEmail(user.getEmail());
        if(user.getCity()!=null) {
            setCityTitle(user.getCity().getName());
            setCountryTitle(user.getCity().getCountry().getName());
        }

        this.phone=user.getPlayer().getPhone();
        this.leadingLeg=user.getPlayer().getLeadingLeg();
        this.roleFootball=user.getPlayer().getRoleFootball();
        this.rolePeople=user.getPlayer().getRolePeople();
        if(user.getPlayer().getClub()!=null) {
            this.club = new AdminClubInfoDto(user.getPlayer().getClub().getUser());
        }
        this.hasAgent=user.getPlayer().getHasAgent();
        if(user.getPlayer().getBirthDate()!=null) {
            this.birthDate = user.getPlayer().getBirthDate().toString();
        }
        this.linkSocialNetwork=user.getPlayer().getLinkSocialNetwork();
    }

}
