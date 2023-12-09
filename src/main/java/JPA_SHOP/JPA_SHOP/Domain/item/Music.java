package JPA_SHOP.JPA_SHOP.Domain.item;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("music")
public class Music extends Item {

  private String artist;
  private String etc;

}
