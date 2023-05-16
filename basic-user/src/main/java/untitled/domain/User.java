package untitled.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import org.springframework.context.ApplicationContext;
import untitled.BasicUserApplication;
import untitled.domain.UserAdd;

@Entity
@Table(name = "User_table")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @Embedded
    @AttributeOverride(
        name = "id",
        column = @Column(name = "departmentId", nullable = true)
    )
    private DepartmentId departmentId;

    @PostPersist
    public void onPostPersist() {
        UserAdd userAdd = new UserAdd(this);
        userAdd.publishAfterCommit();
    }

    public static UserRepository repository() {
        UserRepository userRepository = applicationContext()
            .getBean(UserRepository.class);
        return userRepository;
    }

    public static ApplicationContext applicationContext() {
        return BasicUserApplication.applicationContext;
    }
}
