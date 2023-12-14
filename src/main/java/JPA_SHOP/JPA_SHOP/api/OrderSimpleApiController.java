package JPA_SHOP.JPA_SHOP.api;
import JPA_SHOP.JPA_SHOP.domain.Order;
import JPA_SHOP.JPA_SHOP.repository.OrderRepository;
import JPA_SHOP.JPA_SHOP.repository.OrderSearch;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * xToOne(ManyToOne, OneToOne)
 * Order
 * Order -> Member
 * Order -> Delivery
 * */

@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {

  private final OrderRepository orderRepository;

  @GetMapping("/api/v1/simple-orders")
  public List<Order> ordersV1() {
    List<Order> all = orderRepository.findAllByCriteria(new OrderSearch());
    for (Order order : all) {
      order.getMember().getUsername();
      order.getDelivery().getAddress();
    }
    return all;
  }
}
