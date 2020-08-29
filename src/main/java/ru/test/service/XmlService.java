package ru.test.service;

import org.springframework.stereotype.Service;
import ru.test.dto.StorageDto;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

@Service
public class XmlService {

    public StorageDto unmarshallXml(String fileName) throws JAXBException {
        File file = new File(fileName);
        JAXBContext jaxbContext = JAXBContext.newInstance(StorageDto.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        return (StorageDto) jaxbUnmarshaller.unmarshal(file);
    }

}
