package com.example.practica6rest;

import com.example.practica6rest.model.Product;
import com.example.practica6rest.model.enumeral.ProductType;
import com.example.practica6rest.service.ClientService;
import com.example.practica6rest.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@Slf4j
public class Practice6RestApplication {

    private final ProductService productService;
    private final ClientService clientService;

    @Autowired
    public Practice6RestApplication(ProductService productService, ClientService clientService) {
        this.productService = productService;
        this.clientService = clientService;
    }

    public static void main(String[] args) {
        SpringApplication.run(Practice6RestApplication.class, args);
    }

    @PostConstruct
    private void insertProducts() {

        Product pizza = new Product("Pizza", 10.0, "https://static.sscontent.com/prodimg/thumb/500/500/products/124/v1061874_prozis_4-x-ultra-thin-pizza" +
                "-crusts_4.jpg", false, ProductType.ENTRY);
        Product pizza1 = new Product("Pizza Capricciosa", 12.0, "https://static.sscontent.com/thumb/500/500/products/124/v997357_prozis_artisan-wood-fired" +
                "-pizza-capricciosa_2.jpg", true, ProductType.ENTRY);
        Product pizza2 = new Product("Pizza Saporita", 11.0, "https://tipica.es/ka/apps/tipica/assets/products/saporita2.jpg", true, ProductType.ENTRY);
        Product ramen = new Product("Ramen", 11.0, "https://www.elmundoeats.com/wp-content/uploads/2021/02/FP-Quick-30-minutes-chicken-ramen-500x500.jpg",
                false, ProductType.RAMEN);
        Product ramen1 = new Product("Ramen de pato", 12.0, "https://cdn.recetas360.com/wp-content/uploads/2021/03/receta-de-ramen-coreano-500x500.jpg", true,
                ProductType.RAMEN);
        Product sushi = new Product("Sushi Su", 14.0, "https://blog.dia.es/wp-content/uploads/2020/06/sushi-8V3FQM6-500x500.jpg", true, ProductType.SUSHI);
        Product sushi2 = new Product("Sushi Rolls", 12.0, "https://orimaki.com/wp-content/uploads/2019/03/Orimake-your-roll-5-500x500.jpg", true,
                ProductType.SUSHI);
        Product sushi1 = new Product("Sushi Kimbo", 13.0, "https://www.kimbo.cat/wp-content/uploads/2021/05/KIMBO_26-500x500.jpg", true, ProductType.SUSHI);
        Product kebab = new Product("Kebab", 8.0, "https://www.196flavors.com/wp-content/uploads/2019/09/shish-kebab-2-FP-500x500.jpg", true,
                ProductType.ENTRY);
        Product cola = new Product("Cola", 3.0, "https://speisekarte.menu/storage/media/dishes_main/1301413/coke20500ml-500x500-1531911519.png", true,
                ProductType.DRINK);
        Product pepsi = new Product("Pepsi", 3.0, "https://m.media-amazon.com/images/I/41T+g5zhF3L.jpg", true, ProductType.DRINK);
        Product beer = new Product("Beer", 2.0, "https://correos-marketplace.ams3.cdn.digitaloceanspaces" +
                ".com/prod-new/uploads/correos-marketplace-shop/1/product/6507-e4udy49x-botularium-cerveza-artesana-botularium-variada-2-unidades-de-6-cervezas-distintas-12x0-33cl-13.jpg", true, ProductType.ALCOHOL);
        List<Product> productsToInsert = new ArrayList<>(List.of(pizza, pizza1, pizza2, ramen, sushi, sushi1, sushi2, ramen1, kebab, cola, pepsi, beer));
        List<Product> productsFromDb = productService.getAll();

        ArrayList<Product> listOfEqualProducts = new ArrayList<>();

        try {
            productsToInsert.forEach(product ->
                    productsFromDb.stream()
                            .filter(product1 -> product.getImg_url().equals(product1.getImg_url()))
                            .forEach(listOfEqualProducts::add));
        } catch (NullPointerException e) {
            log.info("there is no info yet");
        }

        if (listOfEqualProducts.isEmpty()) {
            for (Product product : productsToInsert) {
                productService.registry(product);
            }
        }
    }
}
