package co.com.travely.usecase.save;

import co.com.travely.model.products.Product;
import co.com.travely.model.products.gateways.ProductsGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SaveUseCaseTest {

    @Mock
    ProductsGateway gateway;
    SaveUseCase useCase;

    @BeforeEach
    void init(){
        useCase = new SaveUseCase(gateway);
    }

    @Test
    @DisplayName("SaveUseCase")
    void apply() {

        Product product = new Product("testId","test package name", "test details", "test location", 1, new Date(), 2);

        Mockito.when(gateway.saveProduct(product)).thenReturn(Mono.just(product));

        var response = useCase.apply(product);

        StepVerifier.create(response)
                .expectNext(product)
                .expectComplete()
                .verify();

        Mockito.verify(gateway).saveProduct(product);
    }
}