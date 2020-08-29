package ru.test.util;

import ru.test.dto.BoxDto;
import ru.test.dto.ItemDto;
import ru.test.model.BoxEntity;
import ru.test.model.ItemEntity;

import java.util.ArrayList;
import java.util.List;

public class Converter {

    public ItemEntity toModel(ItemDto dto) {
        ItemEntity model = new ItemEntity();
        model.setColor(dto.getColor());
        model.setId(dto.getId());
        return model;
    }

    public List<ItemEntity> toModel(List<ItemDto> dto) {
        List<ItemEntity> list = new ArrayList<>();
        for (ItemDto i : dto) {
            list.add(toModel(i));
        }
        return list;
    }

    public BoxEntity toModel(BoxDto dto) {
        BoxEntity model = new BoxEntity();
        model.setId(dto.getId());
        return model;
    }

    public List<BoxEntity> toModelV(List<BoxDto> dto) {
        BoxEntity model = new BoxEntity();
        for (BoxDto bd : dto) {
            model.setId(bd.getId());
            model.setItem(toModel(bd.getItem()));
            model.setBox(toModelV(bd.getBox()));
        }
        return null;
    }

//	public BoxDto toDto(Box box) {
//		
//	}

}
