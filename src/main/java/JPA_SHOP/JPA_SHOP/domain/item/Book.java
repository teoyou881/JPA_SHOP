package JPA_SHOP.JPA_SHOP.domain.item;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@DiscriminatorValue("book")
public class Book extends Item {

  private String author;
  private String isbn;
}
