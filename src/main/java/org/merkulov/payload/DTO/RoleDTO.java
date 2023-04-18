package org.merkulov.payload.DTO;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class RoleDTO {

    private Map<String,Integer> roleMap= new HashMap<>();
}
