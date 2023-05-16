package untitled.domain;

import java.util.*;
import lombok.*;
import untitled.domain.*;
import untitled.infra.AbstractEvent;

@Data
@ToString
public class UserAdd extends AbstractEvent {

    private Long id;
    private Integer uidx;
    private String tenantId;
    private String useStatus;
    private String pass;
    private String userName;
    private String createBy;
    private String createTm;
    private String lastModiBy;
    private String lastModiTm;
    private DepartmentId departmentId;

    public UserAdd(User aggregate) {
        super(aggregate);
    }

    public UserAdd() {
        super();
    }
}
