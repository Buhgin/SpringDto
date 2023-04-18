package org.merkulov.payload.response;

import lombok.Data;

import java.util.List;
@Data
public class UserResponse {
  //  private List<UserDTO> content;
    private int pageNo;
    private int pageSize;
    private int totalPages;
    private long totalElements;
    private boolean isLast;
}
