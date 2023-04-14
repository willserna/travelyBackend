package co.com.travely.mongo;

import co.com.travely.model.products.Product;
import co.com.travely.mongo.data.ProductData;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizerDefaults;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MongoDBRepository extends ReactiveMongoRepository<ProductData, String>{

    Flux<Product> findByPackageName(String name);
    Flux<Product> findByFeaturedIsTrue();
    Flux<Product> findByCategory(String category);


}
