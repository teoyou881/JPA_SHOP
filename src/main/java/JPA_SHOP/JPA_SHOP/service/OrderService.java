package JPA_SHOP.JPA_SHOP.service;

import JPA_SHOP.JPA_SHOP.domain.Delivery;
import JPA_SHOP.JPA_SHOP.domain.Member;
import JPA_SHOP.JPA_SHOP.domain.Order;
import JPA_SHOP.JPA_SHOP.domain.OrderItem;
import JPA_SHOP.JPA_SHOP.domain.item.Item;
import JPA_SHOP.JPA_SHOP.repository.ItemRepository;
import JPA_SHOP.JPA_SHOP.repository.MemberRepository;
import JPA_SHOP.JPA_SHOP.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

  private final OrderRepository orderRepository;
  private final MemberRepository memberRepository;
  private final ItemRepository itemRepository;

  @Transactional
  public Long order(Long memberId, Long itemId, int count) {

    // find entity
    Member member = memberRepository.findOne(memberId);
    Item item = itemRepository.findOne(itemId);

    //create delivery information
    Delivery delivery = new Delivery();
    delivery.setAddress(member.getAddress());

    //create order item
    OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

    //create order
    Order order = Order.createOrder(member, delivery, orderItem);

    //save order
    /*
     * delivery, orderItem were supposed to be saved on each repository
     * but, cascade option is set to true on Order class
     * so, when order is saved, delivery and orderItem are saved automatically
     * */
    orderRepository.save(order);

    return order.getId();
  }

  // cancel order
  @Transactional
  public void cancelOrder(Long orderId) {
    // find order entity
    Order order = orderRepository.findOne(orderId);
    // cancel order
    order.cancel();
  }

  // search order
}
