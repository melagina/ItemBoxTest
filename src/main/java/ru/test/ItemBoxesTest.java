package ru.test;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import ru.test.logic.StartAppLogic;
import ru.test.util.Converter;

@SpringBootApplication
public class ItemBoxesTest implements CommandLineRunner {

    @Autowired
    private StartAppLogic logic;

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemBoxesTest.class);

    private static ConfigurableApplicationContext ctx;

    public static void main(String[] args) {
        LOGGER.info("start ItemBoxTestApp");
        ctx = SpringApplication.run(ItemBoxesTest.class, args);
    }

    public void run(String... args) {
        try {
        	logic.readAndSave(checkArgsAndGetFileName(args));
        } catch (JAXBException e) {
            LOGGER.error("error parsing xml, try another file\nstop app: ", e);
            finish();
        } catch (IOException e) {
            LOGGER.error("error saving xml data to file: ", e);
        } catch (Throwable t) {
            LOGGER.error("error during execution: ", t);
        } finally {
            LOGGER.info("finish prepare data ItemBoxTestApp");
        }
    }

    private String checkArgsAndGetFileName(String... args) {
        if (args.length == 0) {
            LOGGER.error("error reading file: provide file path!");
            finish();
        }
        if (!checkFilePath(args[0])) {
            LOGGER.error("error reading file: provide correct file path!");
            finish();
        }
        return args[0];
    }

    
    private boolean checkFilePath(String fileName) {
		File f = new File(fileName);
		return f.exists() && !f.isDirectory();
	}

    private void finish() {
    	ctx.close();
    }
}
