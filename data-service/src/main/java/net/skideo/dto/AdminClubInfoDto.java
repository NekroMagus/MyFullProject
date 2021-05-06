package net.skideo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.skideo.model.User;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class AdminClubInfoDto {

    private long id;
    private String name;
    private String surname;
    private String email;
    private String cityTitle;
    private String countryTitle;
    private String logoLink;
    private List<AdminPlayerInfoDto> favoritePlayers;

    public AdminClubInfoDto(User user) {
        setId(user.getId());
        setName(user.getName());
        setSurname(user.getSurname());
        setEmail(user.getEmail());
        if(user.getCity()!=null) {
            setCityTitle(user.getCity().getName());
            setCountryTitle(user.getCity().getCountry().getName());
        }

        this.logoLink=user.getClub().getLogoLink();
        this.favoritePlayers=user.getClub().getFavoritePlayers()
                .stream()
                .map(p -> new AdminPlayerInfoDto(p.getUser()))
                .collect(Collectors.toList());
    }

}
