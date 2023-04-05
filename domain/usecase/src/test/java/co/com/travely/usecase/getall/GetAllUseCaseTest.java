package co.com.travely.usecase.getall;

import co.com.travely.model.products.Product;
import co.com.travely.model.products.gateways.ProductsGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class GetAllUseCaseTest {

    @Mock
    ProductsGateway gateway;
    GetAllUseCase useCase;

    @BeforeEach
    void init(){ useCase = new GetAllUseCase(gateway);}

    @Test
    @DisplayName("GetAllUseCase")
    void get() {

        Mockito.when(gateway.getAllProducts()).thenReturn(Flux.just(new Product(), new Product()));

        var response = useCase.get();

        StepVerifier.create(response)
                .expectNextCount(2)
                .verifyComplete();

        Mockito.verify(gateway).getAllProducts();
    }
}