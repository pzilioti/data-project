package dev.zilioti.daofab;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.zilioti.daofab.entity.Child;
import dev.zilioti.daofab.entity.Parent;
import dev.zilioti.daofab.repository.ParentRepository;
import dev.zilioti.daofab.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootApplication
public class DaofabApplication {

	@Autowired
	DataService dataService;

	public static void main(String[] args) {
		SpringApplication.run(DaofabApplication.class, args);
	}

	//load the json data into the DB
	@Bean
	CommandLineRunner runner() {
		return args -> {
			ObjectMapper mapper = new ObjectMapper();
			//parents
			TypeReference<List<Parent>> typeReference = new TypeReference<>() {};
			InputStream inputStream = TypeReference.class.getResourceAsStream("/json/Parent.json");
			try {
				List<Parent> parents = mapper.readValue(inputStream,typeReference);
				dataService.saveParents(parents);
			} catch (IOException e){
				System.out.println("Unable to save: " + e.getMessage());
			}
			//children
			TypeReference<List<Child>> typeReference2 = new TypeReference<>() {};
			InputStream inputStream2 = TypeReference.class.getResourceAsStream("/json/Child.json");
			try {
				List<Child> children = mapper.readValue(inputStream2,typeReference2);
				dataService.saveChildren(children);
			} catch (IOException e){
				System.out.println("Unable to save: " + e.getMessage());
			}
		};
	}

}
