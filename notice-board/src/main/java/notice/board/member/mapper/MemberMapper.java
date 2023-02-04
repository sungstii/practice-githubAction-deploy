package notice.board.member.mapper;

import notice.board.member.dto.MemberPatchDto;
import notice.board.member.dto.MemberPostDto;
import notice.board.member.dto.MemberResponseDto;
import notice.board.member.entity.Member;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface MemberMapper {
    Member memberPostToMember(MemberPostDto memberPostDto);
    Member memberPatchToMember(MemberPatchDto memberPatchDto);
    MemberResponseDto memberToMemberResponse(Member member);
    List<MemberResponseDto> membersToMemberResponse(List<Member> members);
}
