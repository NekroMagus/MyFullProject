package net.skideo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.skideo.model.User;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class AdminAcademyInfoDto {

    private long id;
    private String name;
    private String surname;
    private String email;
    private String cityTitle;
    private String countryTitle;
    private List<AdminPlayerInfoDto> players;

    public AdminAcademyInfoDto(User user) {
        setId(user.getId());
        setName(user.getName());
        setSurname(user.getSurname());
        setEmail(user.getEmail());
        if(user.getCity()!=null) {
            setCityTitle(user.getCity().getName());
            setCountryTitle(user.getCity().getCountry().getName());
        }

        this.players=user.getAcademy().getPlayers()
                .stream()
                .map(p -> new AdminPlayerInfoDto(p.getUser()))
                .collect(Collectors.toList());
    }

}
