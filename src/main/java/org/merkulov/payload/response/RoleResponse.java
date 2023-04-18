package org.merkulov.payload.response;

import lombok.Data;

import java.util.List;

@Data
public class RoleResponse {
   // private List<RoleDTO> content;
    private int pageNo;
    private int pageSize;
    private int totalPages;
    private long totalElements;
    private boolean isLast;
}
