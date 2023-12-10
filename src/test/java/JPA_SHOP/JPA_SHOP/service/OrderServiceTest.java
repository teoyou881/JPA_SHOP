package JPA_SHOP.JPA_SHOP.service;

import JPA_SHOP.JPA_SHOP.domain.Address;
import JPA_SHOP.JPA_SHOP.domain.Member;
import JPA_SHOP.JPA_SHOP.domain.Order;
import JPA_SHOP.JPA_SHOP.domain.OrderStatus;
import JPA_SHOP.JPA_SHOP.domain.item.Book;
import JPA_SHOP.JPA_SHOP.exception.NotEnoughStockException;
import JPA_SHOP.JPA_SHOP.repository.OrderRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class OrderServiceTest {

  @Autowired
  OrderService orderService;
  @Autowired
  OrderRepository orderRepository;
  @Autowired
  EntityManager em;

  @Test
  public void order() throws Exception {
    //given
    Member member = getMember();

    Book book = getBook("JPA", 10000, 10);

    int orderCount = 2;

    //when
    Long orderId = orderService.order(member.getId(), book.getId(), orderCount);
    //then
    Order getOrder = orderRepository.findOne(orderId);

    Assertions.assertEquals(OrderStatus.ORDER, getOrder.getStatus(),
        "Order status should be ORDER");
    Assertions.assertEquals(1, getOrder.getOrderItems().size(), "Order should have 1 orderItem");
    Assertions.assertEquals(10000 * orderCount, getOrder.getTotalPrice(),
        "Total price should be 10000 * orderCount");
    Assertions.assertEquals(8, book.getStockQuantity(), "Stock quantity should be 8");

  }


  @Test
  public void cancelOrder() throws Exception {
    //given
    Member member = getMember();
    Book book = getBook("JPA", 10000, 10);
    int orderCount = 3;
    Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

    //when
    orderService.cancelOrder(orderId);

    ///then
    Order order = orderRepository.findOne(orderId);
    Assertions.assertEquals(OrderStatus.CANCEL, order.getStatus(), "Order status should be CANCEL");
    Assertions.assertEquals(10, book.getStockQuantity(), "Stock quantity should be 10");

  }

  @Test
  public void OutOfStock() throws Exception {
    //given
    Member member = getMember();
    Book book = getBook("JPA", 10000, 10);
    //when
    int orderCount = 11;
    //then
    try {
      org.assertj.core.api.Assertions.assertThatThrownBy(() -> {
        orderService.order(member.getId(), book.getId(), orderCount);
      }).isInstanceOf(NotEnoughStockException.class);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }


  private Book getBook(String name, int price, int stockQuantity) {
    Book book = new Book();
    book.setName(name);
    book.setPrice(price);
    book.setStockQuantity(stockQuantity);
    em.persist(book);
    return book;
  }

  private Member getMember() {
    Member member = new Member();
    member.setUsername("member1");
    member.setAddress(new Address("30 mabley", "Canada", "ON", "Toronto", "m3x8f9"));
    em.persist(member);
    return member;
  }

}