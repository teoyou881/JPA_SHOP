package JPA_SHOP.JPA_SHOP.controller;

import JPA_SHOP.JPA_SHOP.domain.Member;
import JPA_SHOP.JPA_SHOP.domain.item.Item;
import JPA_SHOP.JPA_SHOP.repository.OrderSearch;
import JPA_SHOP.JPA_SHOP.service.ItemService;
import JPA_SHOP.JPA_SHOP.service.MemberService;
import JPA_SHOP.JPA_SHOP.service.OrderService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class OrderController {

  private final OrderService orderService;
  private final MemberService memberService;
  private final ItemService itemService;

  @GetMapping("/order")
  public String createForm(Model model) {
    List<Member> members = memberService.findMembers();
    List<Item> items = itemService.findItems();
    model.addAttribute("members", members);
    model.addAttribute("items", items);
    return "order/orderForm";
  }

  @PostMapping(value = "/order")
  public String order(@RequestParam("memberId") Long memberId,
      @RequestParam("itemId") Long itemId, @RequestParam("count") int count) {
    orderService.order(memberId, itemId, count);
    return "redirect:/orders";
  }

  @GetMapping("/orders")
  public String orderList(@ModelAttribute("orderSearch") OrderSearch orderSearch, Model model) {
    model.addAttribute("orders", orderService.findOrders(orderSearch));
    return "order/orderList";
  }

  @PostMapping("/orders/{orderId}/cancel")
  public String cancelOrder(@PathVariable("orderId") Long orderId) {
    orderService.cancelOrder(orderId);
    return "redirect:/orders";
  }
}
