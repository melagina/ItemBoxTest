package ru.test.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.test.dto.ItemRequest;
import ru.test.model.BoxEntity;
import ru.test.model.ItemEntity;
import ru.test.repo.BoxRepository;
import ru.test.repo.ItemRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class ResponseService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResponseService.class);

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private BoxRepository boxRepository;

    public Set<Integer> findAllItemsInBoxWithSpecificColor(ItemRequest item) {
        Optional<ItemEntity> it = itemRepository.findById(3);
        LOGGER.info(it.map(itemEntity -> "" + itemEntity + "\n").orElse("no such otem\n"));
        int boxId = item.getParentBoxId();
        Optional<BoxEntity> b = boxRepository.findById(boxId);
        if (!b.isPresent()) {
            LOGGER.info("no boxes with id = {}", boxId);
            return new HashSet<>();
        }
        BoxEntity parentBox = b.get();
        Set<Integer> itemsId = new HashSet<>();

        getAllItems(parentBox, itemsId, item.getColor());
        for (int i : itemsId) {
            LOGGER.info("i {}", i);
        }
        return itemsId;
    }

    private void getAllItems(BoxEntity parentBox, Set<Integer> items, String color) {
        for (ItemEntity i : parentBox.getItem()) {
            if (color.equals(i.getColor()))
                items.add(i.getId());
        }
        for (BoxEntity b : parentBox.getBox()) {
            getAllItems(b, items, color);
        }
    }
}
