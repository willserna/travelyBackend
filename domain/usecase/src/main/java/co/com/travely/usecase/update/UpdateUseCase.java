package co.com.travely.usecase.update;

import co.com.travely.model.products.Product;
import co.com.travely.model.products.gateways.ProductsGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.BiFunction;
import java.util.function.Function;

@RequiredArgsConstructor
public class UpdateUseCase implements BiFunction<String, Product, Mono<Product>> {

    private final ProductsGateway gateway;

    @Override
    public Mono<Product> apply(String id, Product product) {
        return gateway.updateProduct(id, product);
    }
}
