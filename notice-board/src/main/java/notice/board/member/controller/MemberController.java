package notice.board.member.controller;



import lombok.RequiredArgsConstructor;
import notice.board.member.dto.MemberPatchDto;
import notice.board.member.dto.MemberPostDto;
import notice.board.member.dto.MemberResponseDto;
import notice.board.member.entity.Member;
import notice.board.member.mapper.MemberMapper;
import notice.board.member.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberMapper mapper;
    private final MemberService memberService;

    //post member
    @PostMapping
    public ResponseEntity<MemberResponseDto> postMember(@RequestBody MemberPostDto memberPostDto) {
//        memberPostDto -> 엔티티로 바꾸고
//            엔티티 객체를 바탕으로 -> 데이터베이스에 저장 혹은 수정

        Member member = mapper.memberPostToMember(memberPostDto);
        Member saveMember = memberService.createMember(member);

        MemberResponseDto response = mapper.memberToMemberResponse(saveMember);
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }
    //patch member
    @PatchMapping("/{member-id}")
    public ResponseEntity<MemberResponseDto> patchMember(@RequestBody MemberPatchDto memberPatchDto,
                            @PathVariable("member-id") long memberId) {
        // Dto -> entity로 변환하고 entity에 memberId값 (URL로 받은 값) 을 넣어주고 멤버 entity 완성함
        Member member = mapper.memberPatchToMember(memberPatchDto);
        member.setMemberId(memberId);

        Member updateMember = memberService.updateMember(member);

        MemberResponseDto response = mapper.memberToMemberResponse(updateMember);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    //get member
    @GetMapping("/{member-id}")
    public ResponseEntity<MemberResponseDto> getMember(@PathVariable("member-id") long memberId) {
        Member findmember = memberService.findMember(memberId);

        MemberResponseDto response = mapper.memberToMemberResponse(findmember);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    //get members
    @GetMapping
    public ResponseEntity<List<MemberResponseDto>> getMembers() {
        List<Member> findmembers = memberService.findMembers();

        List<MemberResponseDto> response = mapper.membersToMemberResponse(findmembers);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    //delete member
    @DeleteMapping("/{member-id}")
    public void deleteMember(@PathVariable("member-id") long memberId) {
        memberService.deleteMember(memberId);
    }
}
