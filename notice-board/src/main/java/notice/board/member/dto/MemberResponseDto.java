package notice.board.member.dto;

import lombok.Getter;
import lombok.Setter;
import notice.board.member.entity.Member;

@Getter
@Setter
public class MemberResponseDto {
    private String email;
    private String name;
    private String phone;
    private Member.Authority authority;
}
