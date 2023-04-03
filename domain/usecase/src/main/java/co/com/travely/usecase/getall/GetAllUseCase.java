package co.com.travely.usecase.getall;

import co.com.travely.model.products.Product;
import co.com.travely.model.products.gateways.ProductsGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

import java.util.function.Supplier;

@RequiredArgsConstructor
public class GetAllUseCase implements Supplier<Flux<Product>> {

    private final ProductsGateway gateway;

    @Override
    public Flux<Product> get(){
        return gateway.getAllProducts();
    }
}
