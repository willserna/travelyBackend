package co.com.travely.usecase.getbyid;

import co.com.travely.model.products.Product;
import co.com.travely.model.products.gateways.ProductsGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;
import java.util.function.Supplier;

@RequiredArgsConstructor
public class GetByIdUseCase  implements Function<String, Mono<Product>> {

    private final ProductsGateway gateway;


    @Override
    public Mono<Product> apply(String id) {
        return gateway.getProductById(id);
    }
}
