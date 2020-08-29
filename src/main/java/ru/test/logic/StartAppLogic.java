package ru.test.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.test.dto.StorageDto;
import ru.test.exception.DuplicateBoxException;
import ru.test.exception.DuplicateItemException;
import ru.test.service.ExporterService;
import ru.test.service.JPAService;
import ru.test.service.ValidationService;
import ru.test.service.XmlService;

import javax.xml.bind.JAXBException;

@Service
public class StartAppLogic {

    @Autowired
    XmlService xmlService;
    @Autowired
    ValidationService validateService;
    @Autowired
    JPAService jpaService;
    @Autowired
    ExporterService expService;

    public void readAndSave(String fileName) throws JAXBException, DuplicateBoxException, DuplicateItemException {
        StorageDto storage = xmlService.unmarshallXml(fileName);
        validateService.validate(storage);
//        Converter boxConverter = new BoxXmlConverter<BoxDto, BoxEntity>();
//        jpaService.saveToDb(storage.getBox(), storage.getItem());
        expService.exportDataToCsv();
    }

}
