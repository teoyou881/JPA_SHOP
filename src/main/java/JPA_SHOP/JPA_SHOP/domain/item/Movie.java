package JPA_SHOP.JPA_SHOP.domain.item;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@DiscriminatorValue("movie")
public class Movie extends Item {

  private String director;
  private String actor;
}
