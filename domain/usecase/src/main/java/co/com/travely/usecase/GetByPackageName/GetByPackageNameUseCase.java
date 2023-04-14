package co.com.travely.usecase.GetByPackageName;

import co.com.travely.model.products.Product;
import co.com.travely.model.products.gateways.ProductsGateway;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class GetByPackageNameUseCase implements Function<String, Flux<Product>> {

    private final ProductsGateway gateway;

    @Override
    public Flux<Product> apply(String name){
        return gateway.getProductsByPackageName(name);
    }
}
