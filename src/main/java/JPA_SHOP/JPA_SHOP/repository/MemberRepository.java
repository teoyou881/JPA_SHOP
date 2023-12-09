package JPA_SHOP.JPA_SHOP.repository;

import JPA_SHOP.JPA_SHOP.Domain.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository {

  @PersistenceContext
  private EntityManager em;

  public Long save(Member member) {
    em.persist(member);
    // Separate commands and queries.
    return member.getId();
  }

  public Member find(Long id) {
    return em.find(Member.class, id);
  }
}
