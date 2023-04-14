package co.com.travely.mongo.data;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "products")
@NoArgsConstructor
public class ProductData {

    @Id
    private String id;
    private String packageName;
    private String details;
    private String location;
    private Integer stock;
    private Date startDate;
    private Integer persons;
    private Boolean featured;
    private String category;
    private String description;
    private String image;
    private Double price;
}
