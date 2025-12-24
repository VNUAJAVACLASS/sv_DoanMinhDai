package com.vnua.fita.dto.request;

import lombok.Data;
import java.util.Set;

@Data
public class UpdateRoleRequest {
    private Set<String> roles;
}