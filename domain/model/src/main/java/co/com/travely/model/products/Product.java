package co.com.travely.model.products;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Product {

    private String id;
    private String packageName;
    private String details;
    private String location;
    private Integer stock;
    private Date startDate;
    private Integer persons;

}
