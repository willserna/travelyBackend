package co.com.travely.usecase.GetFeatured;

import co.com.travely.model.products.Product;
import co.com.travely.model.products.gateways.ProductsGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;
import java.util.function.Supplier;

@RequiredArgsConstructor
public class GetFeaturedUseCase implements Supplier<Flux<Product>> {

    private final ProductsGateway gateway;

    @Override
        public Flux<Product> get() {
            return gateway.getProductsFeatured();
        }


}
