package studios.tkoh.producto_manager;

import jakarta.annotation.PostConstruct;
import java.util.TimeZone;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductoManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductoManagerApplication.class, args);
    }

    @PostConstruct
    public void init() {
        TimeZone.setDefault(TimeZone.getTimeZone("America/Lima"));
        System.out.println("Zona horaria configurada a: " + new java.util.Date());
    }

}
