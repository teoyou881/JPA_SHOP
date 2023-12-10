package JPA_SHOP.JPA_SHOP.service;

import JPA_SHOP.JPA_SHOP.domain.Member;
import JPA_SHOP.JPA_SHOP.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class MemberServiceTest {

  @Autowired
  MemberService memberService;
  @Autowired
  MemberRepository memberRepository;

  @Test

  public void signUp() throws Exception {
    //given
    Member member = new Member();
    member.setUsername("Teo");
    //when
    Long savedId = memberService.join(member);
    //then
    Assertions.assertThat(member).isEqualTo(memberRepository.findOne(savedId));


  }

  @Test
  public void duplicateNameException() throws Exception {
    //given
    Member member1 = new Member();
    member1.setUsername("member");

    Member member2 = new Member();
    member2.setUsername("member");
    //when
    memberService.join(member1);

    //then
    Assertions.assertThatThrownBy(() -> memberService.join(member2))
        .isInstanceOf(IllegalStateException.class);

  }
}