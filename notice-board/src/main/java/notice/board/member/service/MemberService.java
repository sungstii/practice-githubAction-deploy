package notice.board.member.service;

import lombok.RequiredArgsConstructor;
import notice.board.exception.BusinessLogicException;
import notice.board.exception.ExceptionCode;
import notice.board.member.entity.Member;
import notice.board.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    //create
    public Member createMember(Member member) {
        //컨트롤러에서 멤버객체를 받고 레파지토리에서 저장한다.
        Member createdMember = memberRepository.save(member);
        return createdMember;
    }

    //update
    public Member updateMember(Member member) {
        // 그 멤버 엔티티의 id 값을 레파지토리에서 find함
        //찾으면 나머지 값들이 있으면 수정하고 없으면 넘어감
        // 바꾸고 난 뒤 다시 member 엔티티 저장
        Member update = searchMemberById(member.getMemberId());

        Optional.ofNullable(member.getName()).ifPresent(update :: setName);
        Optional.ofNullable(member.getPassword()).ifPresent(update :: setPassword);
        Optional.ofNullable(member.getPhone()).ifPresent(update::setPhone);

        Member updateMember = memberRepository.save(update);
        return updateMember;
    }


    //find
    public Member findMember(long memberId) {
        Member findedMember = searchMemberById(memberId);
        return findedMember;
    }

    //findAll
    public List<Member> findMembers() {
        List<Member> members = memberRepository.findAll();
        return members;
    }

    //delete
    public void deleteMember(long memberId) {
        memberRepository.deleteById(memberId);
    }

    private Member searchMemberById(long memberId) {
        Optional<Member> optionalMember = memberRepository.findById(memberId);
        Member member = optionalMember.orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
        return member;
    }
}
