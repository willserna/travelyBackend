package co.com.travely.mongo;

import co.com.travely.model.products.Product;
import co.com.travely.model.products.gateways.ProductsGateway;
import co.com.travely.mongo.data.ProductData;
import co.com.travely.mongo.helper.AdapterOperations;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@AllArgsConstructor
public class MongoRepositoryAdapter implements ProductsGateway {

    private final MongoDBRepository productDataRepo;

    private final ObjectMapper mapper;

    @Override
    public Flux<Product> getAllProducts(){

        return this.productDataRepo.findAll()
                .switchIfEmpty(Flux.empty())
                .map(productData -> mapper.map(productData, Product.class));

    }

    @Override
    public Mono<Product> getProductById(String id) {
        return this.productDataRepo
                .findById(id)
                .switchIfEmpty(Mono.empty())
                .map(productData -> mapper.map(productData, Product.class));
    }

    @Override
    public Mono<Product> saveProduct(Product product) {
        return this.productDataRepo.save(mapper.map(product, ProductData.class))
                .switchIfEmpty(Mono.empty())
                .map(productData -> mapper.map(productData, Product.class));
    }

    @Override
    public Mono<String> deleteProduct(String id) {
        return this.productDataRepo
                .findById(id)
                .switchIfEmpty(Mono.empty())
                .flatMap(productData -> this.productDataRepo.deleteById(id).thenReturn(id));
    }

    @Override
    public Mono<Product> updateProduct(String id, Product product) {
        return this.productDataRepo
                .findById(id)
                .switchIfEmpty(Mono.empty())
                .flatMap(productData -> {
                    product.setId(id);
                    return productDataRepo.save(mapper.map(product, ProductData.class));
                })
                .map(productData -> mapper.map(productData, Product.class));
    }





    /**public MongoRepositoryAdapter(MongoDBRepository repository, ObjectMapper mapper) {
        /**
         *  Could be use mapper.mapBuilder if your domain model implement builder pattern
         *  super(repository, mapper, d -> mapper.mapBuilder(d,ObjectModel.ObjectModelBuilder.class).build());
         *  Or using mapper.map with the class of the object model
         *
        super(repository, mapper, d -> mapper.map(d, Object.class/* change for domain model ));

    }*/
}
