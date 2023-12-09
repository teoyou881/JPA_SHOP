package JPA_SHOP.JPA_SHOP.Domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Transactional
public class Member {

  @Id
  @GeneratedValue
  @Column(name = "member_id")
  private Long id;
  private String username;
  @Embedded
  private Address address;

  @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
  private List<Order> orders = new ArrayList<>();

}
