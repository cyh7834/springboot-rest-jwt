package yh.simplejwt.restjwt.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User extends CommonDate{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_num")
    private Long userNum;

    private String id;

    private String email;

    private String password;

    private String nickname;

    @OneToOne(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name = "user_num")
    private UserAuth userAuth;
}
