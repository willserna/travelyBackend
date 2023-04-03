package co.com.travely.mongo;

import co.com.travely.mongo.data.ProductData;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;

public interface MongoDBRepository extends ReactiveMongoRepository<ProductData, String> {
}
