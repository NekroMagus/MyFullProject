package net.skideo.service.file.player;

import au.com.bytecode.opencsv.CSVWriter;
import lombok.RequiredArgsConstructor;
import net.skideo.dto.AdminAcademyInfoDto;
import net.skideo.dto.AdminPlayerInfoDto;
import net.skideo.service.file.FileService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PlayerFileServiceImpl implements FileService<AdminPlayerInfoDto> {

    @Override
    public void writeHeaders(File file) {
        try(CSVWriter csvWriter = new CSVWriter(new FileWriter(file))) {
            csvWriter.writeNext(new String[]
                    {
                            "id", "name", "surname", "email", "cityTitle", "countryTitle",
                            "phone","leadingLeg","roleFootball","rolePeople","club",
                            "hasAgent","birthDate","linkSocialNetwork"
                    });
            csvWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writeData(File file, List<AdminPlayerInfoDto> data) {
        try(CSVWriter csvWriter = new CSVWriter(new FileWriter(file))) {
            for (AdminPlayerInfoDto player : data) {
                csvWriter.writeNext(new String[]
                        {
                                String.valueOf(player.getId()), player.getName(), player.getSurname(), player.getEmail(),
                                player.getCityTitle(), player.getCountryTitle(),player.getPhone(),player.getLeadingLeg().name(),
                                player.getRoleFootball().name(),player.getRolePeople().name(),String.valueOf(player.getClub().getId()),
                                String.valueOf(player.isHasAgent()),player.getBirthDate(),player.getLinkSocialNetwork()
                        });
                csvWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
