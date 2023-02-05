package notice.board.member.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@ToString
@Getter
@Setter
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;
    @Column(unique = true, updatable = false)
    private String email;
    private String name;
    private String password;
    private String phone;
    @Enumerated(EnumType.STRING)
    private Authority authority = Authority.CLIENT;

    public enum Authority {
        ADMIN(1,"관리자"),
        CLIENT(2,"회원");

        private int num;
        private String authority;

        Authority(int num, String authority) {
            this.num = num;
            this.authority = authority;
        }
    }
}
