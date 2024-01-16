package com.example.cub_java_test_yunchunglin.repo;

import com.example.cub_java_test_yunchunglin.entity.ExchangeData;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ExchangeRepository extends MongoRepository<ExchangeData, String> {
}
