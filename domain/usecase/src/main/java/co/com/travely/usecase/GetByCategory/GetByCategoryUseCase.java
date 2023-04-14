package co.com.travely.usecase.GetByCategory;

import co.com.travely.model.products.Product;
import co.com.travely.model.products.gateways.ProductsGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

import java.util.function.Function;

@RequiredArgsConstructor
public class GetByCategoryUseCase implements Function<String, Flux<Product>> {

    private final ProductsGateway gateway;

    @Override
    public Flux<Product> apply(String category){
        return gateway.getByCategory(category);
    }
}

