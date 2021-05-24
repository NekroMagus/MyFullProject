package net.skideo.service.file.academy;

import au.com.bytecode.opencsv.CSVWriter;
import lombok.RequiredArgsConstructor;
import net.skideo.dto.AdminAcademyInfoDto;
import net.skideo.service.file.FileService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AcademyFileServiceImpl implements FileService<AdminAcademyInfoDto> {

    @Override
    public void writeHeaders(File file,CSVWriter csvWriter) throws IOException {
        try {
            csvWriter.writeNext(new String[]
                    {
                            "id", "name", "surname", "email", "cityTitle", "countryTitle", "players"
                    });
            csvWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writeData(File file, List<AdminAcademyInfoDto> data,CSVWriter csvWriter) throws IOException {
        try {
            for (AdminAcademyInfoDto academy : data) {
                csvWriter.writeNext(new String[]
                        {
                                String.valueOf(academy.getId()), academy.getName(), academy.getSurname(),
                                academy.getEmail(), academy.getCityTitle(), academy.getCountryTitle(),
                                academy.getPlayers().stream().map(p -> p.getId()).collect(Collectors.toList()).toString()
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
