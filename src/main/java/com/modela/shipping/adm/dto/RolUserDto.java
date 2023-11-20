package com.modela.shipping.adm.dto;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NamedNativeQuery(
        name = "find_roles_by_user_id",
        query = """
            select aur.role_id, r.name role_name from adm_user_role aur
            inner join adm_role r on aur.role_id = r.role_id
            where aur.user_id = :userId
        """,
        resultSetMapping = "rol_user_dto"
)
@SqlResultSetMapping(
        name = "rol_user_dto",
        classes = @ConstructorResult(
                targetClass = RolUserDto.class,
                columns = {
                        @ColumnResult(name = "role_id", type = Long.class),
                        @ColumnResult(name = "role_name", type = String.class),
                }
        )
)
@Getter @Setter @ToString @Builder
@AllArgsConstructor
@NoArgsConstructor
public class RolUserDto {

    @Id
    @Column(name = "role_id")
    private Long rolId;

    @Column(name = "role_name")
    private String rolName;

}
