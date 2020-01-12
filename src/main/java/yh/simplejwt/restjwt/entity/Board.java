package yh.simplejwt.restjwt.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = {"user"})
public class Board extends CommonDate{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="board_num")
    private Long boardNum;

    private String title;

    private String content;

    @ManyToOne
    @JoinColumn(name="user_num")
    private User user;

    
}