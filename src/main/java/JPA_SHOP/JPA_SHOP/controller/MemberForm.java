package JPA_SHOP.JPA_SHOP.controller;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;

@Getter
@Setter
public class MemberForm {

  @NonNull
  @NotBlank
  private String username;
  private String street;
  private String city;
  private String country;
  private String province;
  private String postalCode;
}
