package com.my_project.API.management_system.config.security;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum RoleEnum {
    IT(1, Set.of(PermissionEnum.LEAVE_WRITE, PermissionEnum.LEAVE_READ)),
    SALE(2, Set.of(PermissionEnum.LEAVE_WRITE, PermissionEnum.LEAVE_READ)),
    HR_MANAGER(3, Set.of(PermissionEnum.LEAVE_READ, PermissionEnum.LEAVE_EDIT, PermissionEnum.STATUS_APPROVED, PermissionEnum.STATUS_REJECTED)),
    CFO(4, Set.of(PermissionEnum.LEAVE_READ, PermissionEnum.LEAVE_EDIT, PermissionEnum.STATUS_APPROVED, PermissionEnum.STATUS_REJECTED)),
    CEO(5, Set.of(PermissionEnum.LEAVE_READ, PermissionEnum.LEAVE_EDIT, PermissionEnum.STATUS_APPROVED, PermissionEnum.STATUS_REJECTED)),
    ADMIN(6, Set.of(PermissionEnum.USER_WRITE, PermissionEnum.USER_READ, PermissionEnum.USER_EDIT, PermissionEnum.USER_DELETE));


    private final int id;
    private final Set<PermissionEnum> permissions;

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return permissions.stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getDescription()))
                .collect(Collectors.toSet());
    }
}
