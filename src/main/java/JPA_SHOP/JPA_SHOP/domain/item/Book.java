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


  public static Book createBookForm(Long id, String name, int price, int stockQuantity,
      String author, String isbn) {
    Book book = new Book();
    book.setId(id);
    book.setName(name);
    book.setPrice(price);
    book.setStockQuantity(stockQuantity);
    book.setAuthor(author);
    book.setIsbn(isbn);
    return book;
  }
}
