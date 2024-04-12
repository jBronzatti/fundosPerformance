package rentabilidade.rentabilidademensal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import rentabilidade.rentabilidademensal.services.RentabilidadeMensalService;

@SpringBootApplication
public class RentabilidadeMensalApplication implements CommandLineRunner {

    private final RentabilidadeMensalService rentabilidadeMensalService;

    @Autowired
    public RentabilidadeMensalApplication(RentabilidadeMensalService rentabilidadeMensalService) {
        this.rentabilidadeMensalService = rentabilidadeMensalService;
    }

    public static void main(String[] args) {
        SpringApplication.run(RentabilidadeMensalApplication.class, args);
    }

    @Override
    public void run(String... arg0) throws Exception {
        rentabilidadeMensalService.calcularRentabilidadeMensal(arg0);
    }
}