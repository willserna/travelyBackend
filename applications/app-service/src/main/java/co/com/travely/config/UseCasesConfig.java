package co.com.travely.config;

import co.com.travely.model.products.gateways.ProductsGateway;
import co.com.travely.usecase.GetByCategory.GetByCategoryUseCase;
import co.com.travely.usecase.GetByPackageName.GetByPackageNameUseCase;
import co.com.travely.usecase.GetFeatured.GetFeaturedUseCase;
import co.com.travely.usecase.delete.DeleteUseCase;
import co.com.travely.usecase.getall.GetAllUseCase;
import co.com.travely.usecase.getbyid.GetByIdUseCase;
import co.com.travely.usecase.save.SaveUseCase;
import co.com.travely.usecase.update.UpdateUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(basePackages = "co.com.travely.usecase")
       /* includeFilters = {
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = "^.+UseCase$")
        },
        useDefaultFilters = false) */
public class UseCasesConfig {

        @Bean
        public GetAllUseCase getAllProductsUseCase(ProductsGateway gateway) {
                return new GetAllUseCase(gateway);
        }

        @Bean
        public GetByIdUseCase getProductByIdUseCase(ProductsGateway gateway) {
                return new GetByIdUseCase(gateway);
        }

        @Bean
        public SaveUseCase saveProductUseCase(ProductsGateway gateway){
                return new SaveUseCase(gateway);
        }

        @Bean
        public UpdateUseCase updateProductUseCase(ProductsGateway gateway){
                return new UpdateUseCase(gateway);
        }

        @Bean
        public DeleteUseCase deleteProductUseCase(ProductsGateway gateway){
                return new DeleteUseCase(gateway);
        }

        @Bean
        GetFeaturedUseCase getFeaturedUseCase(ProductsGateway gateway){
                return new GetFeaturedUseCase(gateway);
        }

        @Bean
        GetByPackageNameUseCase getByPackageNameUseCase(ProductsGateway gateway){
                return new GetByPackageNameUseCase(gateway);
        }

        @Bean
        GetByCategoryUseCase getByCategoryUseCase(ProductsGateway gateway){
                return new GetByCategoryUseCase(gateway);
        }
}
