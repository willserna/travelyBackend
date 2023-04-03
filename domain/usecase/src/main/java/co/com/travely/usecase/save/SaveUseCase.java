package co.com.travely.usecase.save;

import co.com.travely.model.products.Product;
import co.com.travely.model.products.gateways.ProductsGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class SaveUseCase implements Function<Product, Mono<Product>> {

    private final ProductsGateway gateway;
    @Override
    public Mono<Product> apply(Product product) {
        return gateway.saveProduct(product);
    }
}
