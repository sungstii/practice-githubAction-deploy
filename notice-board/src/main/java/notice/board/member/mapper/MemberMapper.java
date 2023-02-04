package notice.board.member;

import notice.board.member.dto.MemberPostDto;
import notice.board.member.entity.Member;
import org.mapstruct.Mapper;



@Mapper(componentModel = "spring")
public interface MemberMapper {
    Member memberPostDtoToMember(MemberPostDto memberPostDto);
}
