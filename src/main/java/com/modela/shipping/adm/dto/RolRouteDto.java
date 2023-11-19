package com.modela.shipping.adm.dto;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NamedNativeQuery(
        name = "find_routes_by_rol_id",
        query = """
            select p.permission_id, p.sref from adm_role_permission rp
            inner join adm_permission p on rp.permission_id = p.permission_id
            where rp.role_id in (:rolIds)
        """,
        resultSetMapping = "rol_routes_dto"
)
@SqlResultSetMapping(
        name = "rol_routes_dto",
        classes = @ConstructorResult(
                targetClass = RolRouteDto.class,
                columns = {
                        @ColumnResult(name = "permission_id", type = Long.class),
                        @ColumnResult(name = "sref", type = String.class),
                }
        )
)
@Getter @Setter @ToString @Builder
@AllArgsConstructor
@NoArgsConstructor
public class RolRouteDto {

    @Id
    @Column(name = "permission_id")
    private Long permissionId;

    @Column(name = "sref")
    private String routeRef;
}
