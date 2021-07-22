package techrovers.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import techrovers.entity.Item;
import techrovers.repository.ItemRepository;
import techrovers.service.ItemService;

@Service
@Transactional
public class ItemServiceImpl implements ItemService{

	@Autowired
	ItemRepository itemRepository;
	
	@Override
	public List<Item> getAllItems() {
		// TODO Auto-generated method stub
		return itemRepository.findAll();
	}

	@Override
	public Item getItemById(Long id) {
		// TODO Auto-generated method stub
		return itemRepository.findById(id).orElseGet(null);
	}

	@Override
	public void saveItem(Item item) {
		// TODO Auto-generated method stub
		itemRepository.save(item);
		
	}

	@Override
	public List<Item> getItems(List<Long> itemIds) {
		// TODO Auto-generated method stub
		return itemRepository.findAllById(itemIds);
	}

}
