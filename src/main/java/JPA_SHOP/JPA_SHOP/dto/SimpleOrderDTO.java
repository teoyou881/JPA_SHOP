package JPA_SHOP.JPA_SHOP.dto;
import JPA_SHOP.JPA_SHOP.domain.Address;
import JPA_SHOP.JPA_SHOP.domain.OrderStatus;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class SimpleOrderDTO {

  private Long orderId;
  private String username;
  private LocalDateTime orderDateTime;
  private OrderStatus orderStatus;
  private Address address;

  public SimpleOrderDTO(Long orderId, String username, LocalDateTime orderDateTime,
      OrderStatus orderStatus, Address address) {
    this.orderId = orderId;
    this.username = username;
    this.orderDateTime = orderDateTime;
    this.orderStatus = orderStatus;
    this.address = address;
  }

}
