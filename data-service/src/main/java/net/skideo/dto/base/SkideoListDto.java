package net.skideo.dto.base;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Data
public class SkideoListDto<T extends Dto> {

    private List<T> data;
    private Paging paging;

    public SkideoListDto(Page<T> data) {
        this.data=data.getContent();
        this.paging=new Paging(data.getPageable().getPageNumber(),data.getPageable().getPageSize(),data.getTotalPages(),data.getTotalElements());
    }

    public SkideoListDto(List<T> data,int pageNumber,int pageSize,int totalPages, int totalElements) {
        this.data=data;
        this.paging=new Paging(pageNumber,pageSize,totalPages,totalElements);
    }

}
