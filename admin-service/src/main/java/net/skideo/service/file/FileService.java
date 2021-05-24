package net.skideo.service.file;

import au.com.bytecode.opencsv.CSVWriter;
import net.skideo.dto.base.Dto;
import java.io.File;
import java.io.IOException;
import java.util.List;

public interface FileService<T extends Dto> {

    void writeHeaders(File file,CSVWriter csvWriter) throws IOException;

    void writeData(File file, List<T> data, CSVWriter csvWriter) throws IOException;

}
