package notice.board.member.dto;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class MemberPatchDto {
    private String email;
    private String name;
    private String password;
    private String phone;
}
