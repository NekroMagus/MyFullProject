package net.skideo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.skideo.dto.shortInfo.AcademyAndClubShortInfoDto;
import net.skideo.dto.shortInfo.PlayerShortInfoDto;
import net.skideo.model.User;
import net.skideo.model.enums.*;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class AdminUserInfoDto {

    /* general */
    private long id;
    private String name;
    private String surname;
    private String email;
    private String cityTitle;
    private String countryTitle;
    private List<PlayerShortInfoDto> players;
    private AcademyAndClubShortInfoDto club;

    /* club */
    private String logoLink;

    /* player */
    private String phone;
    private LeadingLeg leadingLeg;
    private RoleFootball roleFootball;
    private RolePeople rolePeople;
    private Boolean hasAgent;
    private String linkSocialNetwork;
    private String birthDate;

    /* scout */
    private Region region;

    public AdminUserInfoDto(User user) {
        Logger log = Logger.getLogger(AdminUserInfoDto.class.getName());
        setId(user.getId());
        setName(user.getName());
        setSurname(user.getSurname());
        setEmail(user.getEmail());

        if(user.getCity()!=null) {
            setCityTitle(user.getCity().getName());
            setCountryTitle(user.getCity().getCountry().getName());
        }

        if(user.getServiceRole()==ServiceRole.ACADEMY) {
            this.players = user.getAcademy().getPlayers()
                    .stream()
                    .map(PlayerShortInfoDto::new)
                    .collect(Collectors.toList());
        }
        else if(user.getServiceRole()==ServiceRole.CLUB) {
            this.logoLink = user.getClub().getLogoLink();
            this.players = user.getClub().getFavoritePlayers()
                    .stream()
                    .map(PlayerShortInfoDto::new)
                    .collect(Collectors.toList());
        }
        else if(user.getServiceRole()==ServiceRole.PLAYER) {
            this.phone = user.getPlayer().getPhone();
            this.leadingLeg = user.getPlayer().getLeadingLeg();
            this.roleFootball = user.getPlayer().getRoleFootball();
            this.rolePeople = user.getPlayer().getRolePeople();
            if (user.getPlayer().getClub() != null) {
                this.club = new AcademyAndClubShortInfoDto(user.getPlayer().getClub());
            }
            this.hasAgent = user.getPlayer().getHasAgent();
            this.linkSocialNetwork = user.getPlayer().getLinkSocialNetwork();
            if (user.getPlayer().getBirthDate() != null) {
                this.birthDate = user.getPlayer().getBirthDate().toString();
            }
        }
        else if(user.getServiceRole()==ServiceRole.SCOUT) {
            this.region = user.getScout().getRegion();
            this.club = new AcademyAndClubShortInfoDto(user.getScout().getClub());
            this.players = user.getScout().getFavoritePlayers()
                    .stream()
                    .map(PlayerShortInfoDto::new)
                    .collect(Collectors.toList());
        }
    }


}
