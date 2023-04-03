package co.com.travely.api;

import co.com.travely.model.products.Product;
import co.com.travely.usecase.delete.DeleteUseCase;
import co.com.travely.usecase.getall.GetAllUseCase;
import co.com.travely.usecase.getbyid.GetByIdUseCase;
import co.com.travely.usecase.save.SaveUseCase;


import co.com.travely.usecase.update.UpdateUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterRest {
    /*@Bean
    public RouterFunction<ServerResponse> routerFunction(Handler handler) {
        return route(GET("/api/usecase/path"), handler::listenGETUseCase)
                .andRoute(POST("/api/usecase/otherpath"), handler::listenPOSTUseCase)
                .and(route(GET("/api/otherusercase/path"), handler::listenGETOtherUseCase));
    }*/

    @Bean
    @RouterOperation(path = "/products", produces = {
            MediaType.APPLICATION_JSON_VALUE},
            beanClass = GetAllUseCase.class, method = RequestMethod.GET,
            beanMethod = "get",
            operation = @Operation(operationId = "getAll", tags = "Usecases",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success",
                            content = @Content(schema = @Schema(implementation = Product.class))),
                    @ApiResponse(responseCode = "204", description = "Nothing to show")
            }))
    public RouterFunction<ServerResponse> getAllProducts(GetAllUseCase useCase) {
        return route(GET("/products"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(useCase.get(), Product.class))
                        .onErrorResume(throwable -> ServerResponse.noContent().build()));
    }

    @Bean
    @RouterOperation(path = "/products/{id}", produces = {
            MediaType.APPLICATION_JSON_VALUE},
            beanClass = GetByIdUseCase.class, method = RequestMethod.GET,
            beanMethod = "apply",
            operation = @Operation(operationId = "getById", tags = "Usecases",
                    responses = {
                            @ApiResponse(responseCode = "200", description = "Success",
                                    content = @Content(schema = @Schema(implementation = Product.class))),
                            @ApiResponse(responseCode = "404", description = "Product Not Found")
                    }))
    public RouterFunction<ServerResponse> getProductById(GetByIdUseCase useCase){
        return route(GET("/products/{id}"),
                request -> useCase.apply(request.pathVariable("id"))
                        .flatMap(product -> ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(product))
                        .onErrorResume(throwable -> ServerResponse.notFound().build()));
    }

    @Bean
    @RouterOperation(path = "/products", produces = {
            MediaType.APPLICATION_JSON_VALUE},
            beanClass = SaveUseCase.class, method = RequestMethod.POST,
            beanMethod = "apply",
            operation = @Operation(operationId = "save", tags = "Usecases",
                    responses = {
                            @ApiResponse(responseCode = "200", description = "Success",
                                    content = @Content(schema = @Schema(implementation = Product.class))),
                            @ApiResponse(responseCode = "406", description = "Not acceptable")
                    }))
    public RouterFunction<ServerResponse> saveProduct(SaveUseCase useCase) {
        return route(POST("/products").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(Product.class)
                        .flatMap(product -> useCase.apply(product)
                                .flatMap(result -> ServerResponse.status(201)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(result))
                                .onErrorResume(throwable -> ServerResponse.status(HttpStatus.NOT_ACCEPTABLE).bodyValue(throwable.getMessage()))));
    }

    @Bean
    @RouterOperation(path = "/products/{id}", produces = {
            MediaType.APPLICATION_JSON_VALUE},
            beanClass = UpdateUseCase.class, method = RequestMethod.PUT,
            beanMethod = "apply",
            operation = @Operation(operationId = "update", tags = "Usecases",
                    responses = {
                            @ApiResponse(responseCode = "200", description = "Success",
                                    content = @Content(schema = @Schema(implementation = Product.class))),
                            @ApiResponse(responseCode = "404", description = "Product Not Found")
                    }))
    public  RouterFunction<ServerResponse> updateProduct(UpdateUseCase useCase){
        return route(PUT("/products/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(Product.class)
                        .flatMap(product -> useCase.apply(request.pathVariable("id"), product)
                                .flatMap(result -> ServerResponse.status(201)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(result))
                                .onErrorResume(throwable -> ServerResponse.status(HttpStatus.NOT_ACCEPTABLE).bodyValue(throwable.getMessage()))));
    }

    @Bean
    @RouterOperation(path = "/products/{id}", produces = {
            MediaType.APPLICATION_JSON_VALUE},
            beanClass = DeleteUseCase.class, method = RequestMethod.DELETE,
            beanMethod = "apply",
            operation = @Operation(operationId = "delete", tags = "Usecases",
                    responses = {
                            @ApiResponse(responseCode = "200", description = "Success",
                                    content = @Content(schema = @Schema(implementation = Product.class))),
                            @ApiResponse(responseCode = "404", description = "Product Not Found")
                    }))
    public RouterFunction<ServerResponse> deleteProduct(DeleteUseCase useCase) {
        return route(DELETE("/products/{id}"),
                request -> useCase.apply(request.pathVariable("id"))
                        .flatMap(s -> ServerResponse.ok()
                                .bodyValue("Product with id "+s+" has been deleted"))
                        .onErrorResume(throwable -> ServerResponse.notFound().build()));

    }


}
