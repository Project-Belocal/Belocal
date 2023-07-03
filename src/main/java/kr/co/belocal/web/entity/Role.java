package kr.co.belocal.web.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.sql.In;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    private Integer memberId;
    private Integer roleTypeId;


}
