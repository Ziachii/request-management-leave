package com.my_project.API.management_system.config.security;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import static com.my_project.API.management_system.config.security.PermissionEnum.*;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum RoleEnum {
    ADMIN(Set.of(USER_WRITE, USER_READ,USER_EDIT,USER_DELETE)),

    CEO(Set.of(LEAVE_READ, LEAVE_EDIT, STATUS_APPROVED, STATUS_REJECTED)),

    CFO(Set.of(LEAVE_READ, LEAVE_EDIT, STATUS_APPROVED, STATUS_REJECTED)),

    HR_MANAGER(Set.of(LEAVE_READ, LEAVE_EDIT, STATUS_APPROVED, STATUS_REJECTED)),

    IT(Set.of(LEAVE_WRITE,LEAVE_READ)),

    SALE(Set.of(LEAVE_WRITE, LEAVE_READ));

    private Set<PermissionEnum> permissions;

    public Set<SimpleGrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> grantedAuthorities = this.permissions.stream()
                .map(permissions -> new SimpleGrantedAuthority(permissions.getDescription()))
                .collect(Collectors.toSet());
        return grantedAuthorities;
    }
}
