package notice.board.member.dto;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.Column;

@ToString
@Getter
public class MemberPostDto {
    private Long memberId;
    private String email;
    private String name;
    private String password;
    private String phone;
}
