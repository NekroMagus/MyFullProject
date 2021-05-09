package net.skideo.dto.base;

import lombok.Data;
import org.springframework.data.domain.Pageable;

@Data
public class Paging {

    private int pageNumber;
    private int pageSize;
    private int totalPages;
    private long totalElements;

    public Paging(int pageNumber,int pageSize,int totalPages,long totalElements) {
        this.pageNumber=pageNumber;
        this.pageSize=pageSize;
        this.totalPages=totalPages;
        this.totalElements=totalElements;
    }

}
