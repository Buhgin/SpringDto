package org.merkulov.payload.response;

import lombok.Data;
import org.merkulov.payload.DTO.PostDTO;

import java.util.List;

@Data
public class PostResponse {

    private  List<PostDTO> content;
    private int pageNo;
    private int pageSize;
    private int totalPages;
    private long totalElements;
    private boolean isLast;



}
