package ru.test.logic;

import java.io.IOException;

import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.test.converter.BoxXmlConverter;
import ru.test.dto.BoxDto;
import ru.test.dto.StorageDto;
import ru.test.model.BoxEntity;
import ru.test.service.ExporterService;
import ru.test.service.JPAService;
import ru.test.service.ValidationService;
import ru.test.service.XmlService;
import ru.test.util.Converter;

@Service
public class StartAppLogic {

	@Autowired XmlService xmlService;
	@Autowired ValidationService validateService;
	@Autowired JPAService jpaService;
	@Autowired ExporterService expService;
	
	public void readAndSave(String fileName) throws JAXBException, IOException {
		StorageDto storage = xmlService.unmarshallXml(fileName);
		validateService.validate(storage);
		Converter boxConverter = new BoxXmlConverter<BoxDto, BoxEntity>();
//        jpaService.saveToDb(storage.getBox(), storage.getItem());
        expService.exportDataToCsv();
    }
	
}
