package co.com.travely.usecase.update;

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
class UpdateUseCaseTest {

    @Mock
    ProductsGateway gateway;
    UpdateUseCase useCase;

    @BeforeEach
    void init(){
        useCase = new UpdateUseCase(gateway);
    }

    @Test
    @DisplayName("UpdateUseCase")
    void apply() {

        Product product = new Product("testId","test package name", "test details", "test location", 1, new Date(), 2);
        Product update = new Product("testId","test package name updated", "test details", "test location", 1, new Date(), 2);

        //Mockito.when(gateway.getProductById(product.getId())).thenReturn(Mono.just(product));
        Mockito.when(gateway.updateProduct(product.getId(), update)).thenReturn(Mono.just(update));

        var response = useCase.apply(product.getId(), update);

        StepVerifier.create(response)
                .expectNext(update)
                .verifyComplete();

        Mockito.verify(gateway).updateProduct(product.getId(), update);


    }
}