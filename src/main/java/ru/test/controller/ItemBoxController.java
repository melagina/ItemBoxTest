package ru.test.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ru.test.model.ItemRequest;
import ru.test.service.ItemBoxesService;

@RestController
public class ItemBoxController {
	
	@Autowired
	private ItemBoxesService ibService;
	
	@PostMapping("/test")
	public Set<Integer>  findItems(@RequestBody ItemRequest item) {
		return  ibService.findAllItemsInBoxWithSpecificColor(item);
	}

}
