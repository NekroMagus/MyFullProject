package net.skideo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.skideo.dto.base.Dto;
import net.skideo.model.User;
import net.skideo.model.enums.Region;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class AdminScoutInfoDto extends Dto {

    private long id;
    private String name;
    private String surname;
    private String email;
    private String cityTitle;
    private String countryTitle;
    private Region region;
    private AdminClubInfoDto club;
    private List<AdminPlayerInfoDto> favoritePlayers;

    public AdminScoutInfoDto(User user) {
        setId(user.getId());
        setName(user.getName());
        setSurname(user.getSurname());
        setEmail(user.getEmail());
        if(user.getCity()!=null) {
            setCityTitle(user.getCity().getName());
            setCountryTitle(user.getCity().getCountry().getName());
        }

        this.region=user.getScout().getRegion();
        this.club=new AdminClubInfoDto(user.getScout().getClub().getUser());
        this.favoritePlayers=user.getScout().getFavoritePlayers()
                .stream()
                .map(p -> new AdminPlayerInfoDto(p.getUser()))
                .collect(Collectors.toList());
    }

}
