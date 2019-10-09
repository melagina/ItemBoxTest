package ru.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
@EnableConfigurationProperties
public class ExporterService {

	@Autowired private JdbcTemplate jdbcTemplate;
	
	@Value("${app.csv.file.box}") private String boxCsvFile;
    @Value("${app.csv.file.item}") private String itemCsvFile;
	
	public void exportDataToCsv() {
        jdbcTemplate.execute("call CSVWRITE ( '" + boxCsvFile + "', 'SELECT * FROM box; SELECT * FROM item', 'charset=UTF-8' )");
        jdbcTemplate.execute("call CSVWRITE ( '" + itemCsvFile + "', 'SELECT * FROM item', 'charset=UTF-8' )");
    }
}
