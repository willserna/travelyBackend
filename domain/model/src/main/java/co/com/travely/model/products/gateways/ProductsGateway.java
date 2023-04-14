package co.com.travely.model.products.gateways;

import co.com.travely.model.products.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductsGateway {

    Flux<Product> getAllProducts();

    Mono<Product> getProductById(String id);

    Mono<Product> saveProduct(Product product);

    Mono<String> deleteProduct(String id);

    Mono<Product> updateProduct(String id, Product product);
    Flux<Product> getProductsByCategory(String category);
    Flux<Product> getProductsByPackageName(String name);
    Flux<Product> getProductsByLocation(String location);
    Flux<Product> getProductsFeatured();

    Flux<Product> getByCategory(String category);
}
