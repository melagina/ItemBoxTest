package ru.test.service;

import org.springframework.stereotype.Service;
import ru.test.dto.BoxDto;
import ru.test.dto.ItemDto;
import ru.test.dto.StorageDto;
import ru.test.exception.DuplicateBoxException;
import ru.test.exception.DuplicateItemException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ValidationService {

    public void validate(StorageDto storage) throws DuplicateItemException, DuplicateBoxException {
        Set<Integer> boxIdSet = new HashSet<>();
        Set<Integer> itemIdSet = new HashSet<>();
        checkItems(storage.getItem(), itemIdSet);
        checkBoxes(storage.getBox(), boxIdSet, itemIdSet);
    }

    private void checkBoxes(List<BoxDto> box, Set<Integer> boxIdSet, Set<Integer> itemIdSet) throws DuplicateItemException, DuplicateBoxException {
        for (BoxDto b : box) {
            if (!boxIdSet.add(b.getId())) {
                throw new DuplicateBoxException(b.getId());
            }
            checkItems(b.getItem(), itemIdSet);
            checkBoxes(b.getBox(), boxIdSet, itemIdSet);
        }
    }

    private void checkItems(List<ItemDto> item, Set<Integer> itemIdSet) throws DuplicateItemException {

        for (ItemDto it : item) {
            if (!itemIdSet.add(it.getId())) {
                throw new DuplicateItemException(it.getId());
            }
        }
    }
}
