package org.merkulov.payload.response;

import lombok.Data;
import org.merkulov.payload.DTO.CommentDTO;

import java.util.List;
@Data
public class CommentResponse {
    private List<CommentDTO> content;
    private int pageNo;
    private int pageSize;
    private int totalPages;
    private long totalElements;
    private boolean isLast;

}
