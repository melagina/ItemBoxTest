package ru.test.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ru.test.dto.ItemRequest;
import ru.test.service.ResponseService;

@RestController
public class ItemBoxController {
	
	@Autowired
	private ResponseService service;
	
	@PostMapping("/test")
	public Set<Integer>  findItems(@RequestBody ItemRequest item) {
		return  service.findAllItemsInBoxWithSpecificColor(item);
	}

}
