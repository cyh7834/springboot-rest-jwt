package yh.simplejwt.restjwt.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = {"user", "board"})
public class Reply extends CommonDate{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long replyNum;

    private String content;

    @ManyToOne
    @JoinColumn(name="user_num")
    private User user;

    @ManyToOne
    @JoinColumn(name="board_num")
    private Board board;
}