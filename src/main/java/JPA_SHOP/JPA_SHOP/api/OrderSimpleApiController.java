package JPA_SHOP.JPA_SHOP.api;
import JPA_SHOP.JPA_SHOP.domain.Address;
import JPA_SHOP.JPA_SHOP.domain.Order;
import JPA_SHOP.JPA_SHOP.domain.OrderStatus;
import JPA_SHOP.JPA_SHOP.repository.OrderRepository;
import JPA_SHOP.JPA_SHOP.repository.OrderSearch;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Data;
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

  @GetMapping("/api/v2/simple-orders")
  public List<SimpleOrderDTO> ordersV2() {
    List<Order> all = orderRepository.findAllByCriteria(new OrderSearch());
    return all.stream()
              .map(SimpleOrderDTO::new)
              .collect(Collectors.toList());
  }

  @GetMapping("/api/v3/simple-orders")
  public List<SimpleOrderDTO> ordersV3() {
    List<Order> all = orderRepository.findAllWithMemberDelivery();
    return all.stream()
              .map(SimpleOrderDTO::new)
              .collect(Collectors.toList());
  }

  @GetMapping("/api/v4/simple-orders")
  public List<JPA_SHOP.JPA_SHOP.dto.SimpleOrderDTO> ordersV4() {
    return orderRepository.findOrderDTOs();
  }

  @Data
  public class SimpleOrderDTO {

    private Long orderId;
    private String username;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private Address address;

    public SimpleOrderDTO(Order order) {
      orderId = order.getId();
      username = order.getMember().getUsername();
      orderDate = order.getOrderDatetime();
      orderStatus = order.getStatus();
      address = order.getDelivery().getAddress();
    }
  }
}
