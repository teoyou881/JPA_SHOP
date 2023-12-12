package JPA_SHOP.JPA_SHOP.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;

@Getter
@Setter
public class MemberForm {

  @NonNull
  private String username;
  private String street;
  private String country;
  private String province;
  private String city;
  private String postalCode;
}
