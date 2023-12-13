package JPA_SHOP.JPA_SHOP.service;

import JPA_SHOP.JPA_SHOP.domain.item.Item;
import JPA_SHOP.JPA_SHOP.repository.ItemRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

  private final ItemRepository itemRepository;

  @Transactional
  public void saveItem(Item item) {
    itemRepository.save(item);
  }

  @Transactional
  public void updateItem(Long itemId, String name, int price, int stockQuantity) {
    Item item = itemRepository.findOne(itemId);
    item.setName(name);
    item.setPrice(price);
    item.setStockQuantity(stockQuantity);
  }

  public Item findOne(Long itemId) {
    return itemRepository.findOne(itemId);
  }

  public List<Item> findItems() {
    return itemRepository.findAll();
  }
}
