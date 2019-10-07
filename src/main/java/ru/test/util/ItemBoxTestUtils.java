package ru.test.util;

import java.io.File;
import java.io.StringWriter;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;


import ru.test.model.Box;
import ru.test.model.Item;
import ru.test.model.Storage;

public class ItemBoxTestUtils {

	public static boolean checkFilePath(String fileName) {
		File f = new File(fileName);
		return f.exists() && !f.isDirectory();
	}
	
	public static Storage ummarshallXml(String fileName) throws JAXBException {
		File file = new File(fileName);
		JAXBContext jaxbContext = JAXBContext.newInstance(Storage.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		Storage storage = (Storage) jaxbUnmarshaller.unmarshal(file);
		System.out.println(storage);
		return storage;
	}
	
	public static void setBoxParent(Box parentBox, Optional<List<Box>> boxesOpt) {
		if (!boxesOpt.isPresent()) return;
		List<Box> boxes = boxesOpt.get();
			for (Box b: boxes) {
				b.setParentBox(parentBox);
				setBoxParent(b, Optional.ofNullable(b.getBox()));
				setItemParent(b, Optional.ofNullable(b.getItem()));
			}
	}

	public static void setItemParent(Box parentBox, Optional<List<Item>> itemsOpt) {
		if (!itemsOpt.isPresent()) return;
		List<Item> items = itemsOpt.get();
		for (Item i: items) {
			i.setParentBox(parentBox);
		}
	}

	public static void getAllItems(Box parentBox, Set<Integer> items, String color) {
		for (Item i : parentBox.getItem()) {
			if (color.equals(i.getColor()))
				items.add(i.getId());
		}
		for (Box b : parentBox.getBox()) {
			getAllItems(b, items, color);
		}
	}

	public static void marshallStorage(Storage storage) throws JAXBException {
		StringWriter writer = new StringWriter();
		JAXBContext context = JAXBContext.newInstance(Storage.class);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		marshaller.marshal(storage, writer);
		String result = writer.toString();
		System.out.println(result);
	}

}
