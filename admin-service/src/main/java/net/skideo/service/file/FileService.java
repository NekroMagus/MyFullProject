package net.skideo.service.file;

import net.skideo.dto.base.Dto;
import java.io.File;
import java.util.List;

public interface FileService<T extends Dto> {

    void writeHeaders(File file);

    void writeData(File file, List<T> data);

}
