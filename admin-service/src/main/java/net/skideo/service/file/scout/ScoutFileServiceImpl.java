package net.skideo.service.file.scout;

import au.com.bytecode.opencsv.CSVWriter;
import lombok.RequiredArgsConstructor;
import net.skideo.dto.AdminScoutInfoDto;
import net.skideo.service.file.FileService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ScoutFileServiceImpl implements FileService<AdminScoutInfoDto> {

    @Override
    public void writeHeaders(File file, CSVWriter csvWriter) throws IOException {
        try {
            csvWriter.writeNext(new String[]
                    {
                            "id", "name", "surname", "email", "cityTitle",
                            "countryTitle","region","club","favoritePlayers"
                    });
            csvWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writeData(File file, List<AdminScoutInfoDto> data, CSVWriter csvWriter) throws IOException {
        try {
            for (AdminScoutInfoDto scout : data) {
                csvWriter.writeNext(new String[]
                        {
                                String.valueOf(scout.getId()), scout.getName(), scout.getSurname(), scout.getEmail(),
                                scout.getCityTitle(), scout.getCountryTitle(),scout.getRegion().name(),String.valueOf(scout.getClub().getId()),
                                scout.getFavoritePlayers().stream().map(p -> p.getId()).collect(Collectors.toList()).toString()
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
