package com.example.cub_java_test_yunchunglin;

import com.example.cub_java_test_yunchunglin.entity.ExchangeData;
import com.example.cub_java_test_yunchunglin.repo.ExchangeRepository;
import com.example.cub_java_test_yunchunglin.service.RateAPIService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

@SpringBootApplication
public class CubJavaTestYunchunglinApplication {

	public static void main(String[] args) {
		SpringApplication.run(CubJavaTestYunchunglinApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(ExchangeRepository repository, RateAPIService rateAPIService, MongoTemplate mongoTemplate){
		return args -> {

			//check for previous data
			List<ExchangeData> datas = mongoTemplate.find(new Query(), ExchangeData.class);
			if(!datas.isEmpty()){
				return;
			}

			//get records from API upon startup if no data
			List<ExchangeData> firstFetch = rateAPIService.fetch();

			//insert all data
			for(ExchangeData d : firstFetch){
				System.out.println("Inserting data" + d);
				repository.insert(d);
		}
			};
		}
	}
