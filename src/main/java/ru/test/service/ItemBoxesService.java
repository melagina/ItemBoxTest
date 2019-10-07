package ru.test.service;

import java.io.IOException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.xml.bind.JAXBException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.test.repo.BoxRepository;
import ru.test.repo.ItemRepository;
import ru.test.model.Box;
import ru.test.model.Item;
import ru.test.model.ItemRequest;
import ru.test.model.Storage;
import ru.test.util.ItemBoxTestUtils;

@Service
@EnableConfigurationProperties
public class ItemBoxesService {

    @Autowired private ItemRepository itemRepository;
    @Autowired private BoxRepository boxRepository;
    @Autowired private JdbcTemplate jdbcTemplate;

    @Value("${app.csv.file.box}")  private String boxCsvFile;
    @Value("${app.csv.file.item}") private String itemCsvFile;

    private Storage storage;

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemBoxesService.class);


    public void readAndSave(String fileName) throws JAXBException, IOException {
        readXml(fileName);
        saveToDb();
        exportDataToCsv();
    }

    private void readXml(String fileName) throws JAXBException {
        storage = ItemBoxTestUtils.ummarshallXml(fileName);
    }

    @Transactional(rollbackFor = Exception.class)
    private void saveToDb() {
        ItemBoxTestUtils.setBoxParent(null, Optional.ofNullable(storage.getBox()));
        ItemBoxTestUtils.setItemParent(null, Optional.ofNullable(storage.getItem()));
        System.out.println(storage);
        for (Box box : storage.getBox()) {
            boxRepository.save(box);
        }
        for (Item item : storage.getItem()) {
            itemRepository.save(item);
        }
    }

    private void exportDataToCsv() {
        jdbcTemplate.execute("call CSVWRITE ( '" + boxCsvFile + "', 'SELECT * FROM box; SELECT * FROM item', 'charset=UTF-8' )");
        jdbcTemplate.execute("call CSVWRITE ( '" + itemCsvFile + "', 'SELECT * FROM item' )");
    }

    public Set<Integer> findAllItemsInBoxWithSpecificColor(ItemRequest item) {
        Optional<Item> it = itemRepository.findById(3);
        LOGGER.info(it.isPresent() ? "" + it.get() + "\n" : "no such otem\n");
        Optional<Box> b = boxRepository.findById(item.getBox());
        if (!b.isPresent()) {
            LOGGER.info("no boxes with id = {}", item.getBox());
            return new HashSet<Integer>();
        }
        Box parentBox = b.get();
        Set<Integer> itemsId = new HashSet<>();

        ItemBoxTestUtils.getAllItems(parentBox, itemsId, item.getColor());
        for (int i : itemsId) {
            LOGGER.info("i {}", i);
        }
        return itemsId;
    }

}
