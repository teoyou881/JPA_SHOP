package JPA_SHOP.JPA_SHOP.service;

import JPA_SHOP.JPA_SHOP.domain.Member;
import JPA_SHOP.JPA_SHOP.repository.MemberRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

  private final MemberRepository memberRepository;

  @Transactional
  public Long join(Member member) {
    validateDuplicateMember(member);
    memberRepository.save(member);
    return member.getId();
  }

  public List<Member> findMembers() {
    return memberRepository.findAll();
  }

  public Member findOne(Long memberId) {
    return memberRepository.findOne(memberId);
  }

  private void validateDuplicateMember(Member member) {
    // Exception if there is a member with the same name.
    List<Member> byName = memberRepository.findByName(member.getUsername());
    if (!byName.isEmpty()) {
      throw new IllegalStateException("name already exists.");
    }
  }
}
