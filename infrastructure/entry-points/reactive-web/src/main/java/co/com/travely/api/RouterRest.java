package co.com.travely.api;

import co.com.travely.model.products.Product;
import co.com.travely.usecase.GetByCategory.GetByCategoryUseCase;
import co.com.travely.usecase.GetByPackageName.GetByPackageNameUseCase;
import co.com.travely.usecase.GetFeatured.GetFeaturedUseCase;
import co.com.travely.usecase.delete.DeleteUseCase;
import co.com.travely.usecase.getall.GetAllUseCase;
import co.com.travely.usecase.getbyid.GetByIdUseCase;
import co.com.travely.usecase.save.SaveUseCase;


import co.com.travely.usecase.update.UpdateUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
                    @ApiResponse(responseCode = "204", description = "Database Empty")
            }))
    public RouterFunction<ServerResponse> getAllProducts(GetAllUseCase useCase) {
        return route(GET("/products"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(useCase.get(), Product.class))
                        .onErrorResume(throwable -> ServerResponse.noContent().build()));
    }

    @Bean
    @RouterOperation(path = "/products/featured", produces = {
            MediaType.APPLICATION_JSON_VALUE},
            beanClass = GetFeaturedUseCase.class, method = RequestMethod.GET,
            beanMethod = "get",
            operation = @Operation(operationId = "getFeatured", tags = "Usecases",
                    responses = {
                            @ApiResponse(responseCode = "200", description = "Success",
                                    content = @Content(schema = @Schema(implementation = Product.class))),
                            @ApiResponse(responseCode = "204", description = "Database Empty")
                    }))
    public RouterFunction<ServerResponse> getFeaturedProducts(GetFeaturedUseCase useCase) {
        return route(GET("/products/featured"),
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
                    parameters = {
                            @Parameter(name = "id", description = "Product ID", required = true, in = ParameterIn.PATH)

                    },
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
    @RouterOperation(path = "/products/packagename/{name}", produces = {
            MediaType.APPLICATION_JSON_VALUE},
            beanClass = GetByPackageNameUseCase.class, method = RequestMethod.GET,
            beanMethod = "apply",
            operation = @Operation(operationId = "getByPackageName", tags = "Usecases",
                    parameters = {
                            @Parameter(name = "name", description = "Product Name", required = true, in = ParameterIn.PATH)

                    },
                    responses = {
                            @ApiResponse(responseCode = "200", description = "Success",
                                    content = @Content(schema = @Schema(implementation = Product.class))),
                            @ApiResponse(responseCode = "404", description = "Product Not Found")
                    }))
    public RouterFunction<ServerResponse> getProductByPackageName(GetByPackageNameUseCase useCase){
        return route(GET("/products/packagename/{name}"),
                request -> useCase.apply(request.pathVariable("name"))
                        .collectList()
                        .flatMap(products -> ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(products))
                        .onErrorResume(throwable -> ServerResponse.noContent().build()));
    }

    @Bean
    @RouterOperation(path = "/products/category/{category}", produces = {
            MediaType.APPLICATION_JSON_VALUE},
            beanClass = GetByCategoryUseCase.class, method = RequestMethod.GET,
            beanMethod = "apply",
            operation = @Operation(operationId = "getByCategory", tags = "Usecases",
                    parameters = {
                            @Parameter(name = "category", description = "Product category", required = true, in = ParameterIn.PATH)

                    },
                    responses = {
                            @ApiResponse(responseCode = "200", description = "Success",
                                    content = @Content(schema = @Schema(implementation = Product.class))),
                            @ApiResponse(responseCode = "404", description = "Product Not Found")
                    }))
    public RouterFunction<ServerResponse> getProductByCategory(GetByCategoryUseCase useCase){
        return route(GET("/products/category/{category}"),
                request -> useCase.apply(request.pathVariable("category"))
                        .collectList()
                        .flatMap(products -> ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(products))
                        .onErrorResume(throwable -> ServerResponse.noContent().build()));
    }

    @Bean
    @RouterOperation(path = "/products", produces = {
            MediaType.APPLICATION_JSON_VALUE},
            beanClass = SaveUseCase.class, method = RequestMethod.POST,
            beanMethod = "apply",
            operation = @Operation(operationId = "save", tags = "Usecases",
                    parameters = {
                            @Parameter(name = "product", in = ParameterIn.PATH, schema = @Schema(implementation = Product.class))
                    },
                    responses = {
                            @ApiResponse(responseCode = "201", description = "Success",
                                    content = @Content(schema = @Schema(implementation = Product.class))),
                            @ApiResponse(responseCode = "406", description = "Not acceptable")
                    },
                    requestBody = @RequestBody(required = true, description = "Save a product with this schema", content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Product.class)))))
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
                    parameters = {
                        @Parameter(name = "id", description = "Product ID", required = true, in = ParameterIn.PATH),
                        @Parameter(name = "product", in = ParameterIn.PATH, schema = @Schema(implementation = Product.class))
                    },
                    responses = {
                            @ApiResponse(responseCode = "201", description = "Success",
                                    content = @Content(schema = @Schema(implementation = Product.class))),
                            @ApiResponse(responseCode = "404", description = "Product Not Found")
                    },
                    requestBody = @RequestBody(required = true, description = "Update a product with this schema", content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Product.class)))))
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
                    parameters = {
                            @Parameter(name = "id", description = "Product ID", required = true, in = ParameterIn.PATH)
                    },
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
