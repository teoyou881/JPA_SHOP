package JPA_SHOP.JPA_SHOP.controller;

import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

@Getter
@Setter
public class MemberForm {

  @NotNull
  private String username;
  private String street;
  private String country;
  private String province;
  private String city;
  private String postalCode;
}
