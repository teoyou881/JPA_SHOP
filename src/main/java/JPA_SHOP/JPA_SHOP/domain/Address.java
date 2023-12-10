package JPA_SHOP.JPA_SHOP.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class Address {

  private String street;
  private String country;
  private String province;
  private String city;
  private String postalCode;

  protected Address() {
  }

  public Address(String street, String country, String province, String city, String postalCode) {
    this.street = street;
    this.country = country;
    this.province = province;
    this.city = city;
    this.postalCode = postalCode;
  }
}
