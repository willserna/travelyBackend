package co.com.travely.usecase.delete;

import co.com.travely.model.products.gateways.ProductsGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class DeleteUseCase implements Function<String, Mono<String>> {

    private final ProductsGateway gateway;

    @Override
    public Mono<String> apply(String id) {
        return gateway.deleteProduct(id);
    }
}
