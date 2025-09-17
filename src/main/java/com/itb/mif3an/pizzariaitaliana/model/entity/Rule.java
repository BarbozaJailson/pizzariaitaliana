package com.itb.mif3an.pizzariaitaliana.model.entity;

import java.util.Set;

import static com.itb.mif3an.pizzariaitaliana.model.entity.Permission.*;

public enum Rule {
    ADMIN(
            Set.of(
                    ADMIN_READ,
                    ADMIN_UPDATE,
                    ADMIN_CREATE,
                    ADMIN_DELETE,
                    FUNCIONARIO_READ,
                    FUNCIONARIO_UPDATE,
                    FUNCIONARIO_CREATE,
                    FUNCIONARIO_DELETE
            )
    ),
    CLIENTE(
            Set.of(
                    CLIENTE_READ,
                    CLIENTE_UPDATE,
                    CLIENTE_CREATE,
                    CLIENTE_DELETE
            )
    ),
    FUNCIONARIO(
            Set.of(
                    FUNCIONARIO_READ,
                    FUNCIONARIO_UPDATE,
                    FUNCIONARIO_CREATE,
                    FUNCIONARIO_DELETE
            )
    );
    private final Set<Permission> permissions;
    Rule(Set<Permission> permissions) {this.permissions = permissions;};
}
