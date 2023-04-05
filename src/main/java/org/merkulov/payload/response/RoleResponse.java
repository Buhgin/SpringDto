package org.merkulov.payload.response;

import lombok.Data;
import org.merkulov.payload.DTO.RoleDTO;
import org.merkulov.payload.DTO.UserDTO;

import java.util.List;

@Data
public class RoleResponse {
    private List<RoleDTO> content;
    private int pageNo;
    private int pageSize;
    private int totalPages;
    private long totalElements;
    private boolean isLast;
}
