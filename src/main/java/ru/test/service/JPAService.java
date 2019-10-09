package ru.test.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import ru.test.model.BoxEntity;
import ru.test.model.ItemEntity;
import ru.test.repo.BoxRepository;
import ru.test.repo.ItemRepository;

public class JPAService {

	@Autowired private ItemRepository itemRepository;
	@Autowired private BoxRepository boxRepository;

	@Transactional(rollbackFor = Exception.class)
	public void saveToDb(List<BoxEntity> boxes, List<ItemEntity> items) {
		setParentBox(null, Optional.ofNullable(boxes));
		setParentBoxForItems(null, Optional.ofNullable(items));
		for (BoxEntity box : boxes) {
			boxRepository.save(box);
		}
		for (ItemEntity item : items) {
			itemRepository.save(item);
		}
	}
	
	public static void setParentBox(BoxEntity parentBox, Optional<List<BoxEntity>> boxesOpt) {
		if (!boxesOpt.isPresent()) {
			return;
		}
		List<BoxEntity> boxes = boxesOpt.get();
			for (BoxEntity b: boxes) {
				b.setParentBox(parentBox);
				setParentBox(b, Optional.ofNullable(b.getBox()));
				setParentBoxForItems(b, Optional.ofNullable(b.getItem()));
			}
	}

	public static void setParentBoxForItems(BoxEntity parentBox, Optional<List<ItemEntity>> itemsOpt) {
		if (!itemsOpt.isPresent()) {
			return;
		}
		List<ItemEntity> items = itemsOpt.get();
		for (ItemEntity i: items) {
			i.setParentBox(parentBox);
		}
	}
}
