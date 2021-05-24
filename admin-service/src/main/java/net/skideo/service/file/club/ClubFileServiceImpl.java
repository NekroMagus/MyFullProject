package net.skideo.service.file.club;

import au.com.bytecode.opencsv.CSVWriter;
import lombok.RequiredArgsConstructor;
import net.skideo.dto.AdminClubInfoDto;
import net.skideo.service.file.FileService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ClubFileServiceImpl implements FileService<AdminClubInfoDto> {

    @Override
    public void writeHeaders(File file, CSVWriter csvWriter) throws IOException {
        try {
            csvWriter.writeNext(new String[]{"id", "name", "surname", "email", "cityTitle", "countryTitle","logoLink","favoritePlayers"});
            csvWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writeData(File file, List<AdminClubInfoDto> data, CSVWriter csvWriter) throws IOException {
        try {
            for (AdminClubInfoDto club : data) {
                csvWriter.writeNext(new String[]
                        {
                                String.valueOf(club.getId()), club.getName(), club.getSurname(),
                                club.getEmail(), club.getCityTitle(), club.getCountryTitle(),club.getLogoLink(),
                                club.getFavoritePlayers().stream().map(p -> p.getId()).collect(Collectors.toList()).toString()
                        });
                csvWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            csvWriter.close();
        }
    }
}
