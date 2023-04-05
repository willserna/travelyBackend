package co.com.travely.usecase.delete;

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
class DeleteUseCaseTest {

    @Mock
    ProductsGateway gateway;
    DeleteUseCase useCase;

    @BeforeEach
    void init(){
        useCase = new DeleteUseCase(gateway);
    }

    @Test
    @DisplayName("DeleteUseCase")
    void apply() {

        Product product = new Product("testId","test package name", "test details", "test location", 1, new Date(), 2);

        Mockito.when(gateway.deleteProduct(product.getId())).thenReturn(Mono.empty());

        var response = useCase.apply(product.getId());

        StepVerifier.create(response)
                .expectComplete()
                .verify();

    }
}