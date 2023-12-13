package JPA_SHOP.JPA_SHOP.controller;

import JPA_SHOP.JPA_SHOP.domain.item.Book;
import JPA_SHOP.JPA_SHOP.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ItemController {

  private final ItemService itemService;

  @GetMapping("/items/new")
  public String createForm(Model model) {
    model.addAttribute("form", new BookForm());
    return "items/createItemForm";
  }

  @PostMapping("/items/new")
  public String create(BookForm form) {
    Book book = Book.createBookForm(form.getId(), form.getName(), form.getPrice(),
        form.getStockQuantity(),
        form.getAuthor(), form.getIsbn());

    itemService.saveItem(book);
    return "redirect:/";
  }

  @GetMapping("/items")
  public String list(Model model) {
    model.addAttribute("items", itemService.findItems());
    return "items/itemList";
  }

  @GetMapping("/items/{itemId}/edit")
  public String updateItemForm(@PathVariable("itemId") Long itemId, Model model) {
    Book item = (Book) itemService.findOne(itemId);

    BookForm form = new BookForm();
    form.setId(item.getId());
    form.setName(item.getName());
    form.setPrice(item.getPrice());
    form.setStockQuantity(item.getStockQuantity());
    form.setAuthor(item.getAuthor());
    form.setIsbn(item.getIsbn());

    model.addAttribute("form", form);
    return "items/updateItemForm";
  }

  @PostMapping("/items/{itemId}/edit")
  public String updateItem(@ModelAttribute BookForm form) {

    itemService.updateItem(form.getId(), form.getName(), form.getPrice(), form.getStockQuantity());
    
    return "redirect:/items";

  }
}
