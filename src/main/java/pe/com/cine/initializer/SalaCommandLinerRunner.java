package pe.com.cine.initializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import pe.com.cine.entities.Sala;
import pe.com.cine.repositories.SalaRepository;

@Component
public class SalaCommandLinerRunner  implements CommandLineRunner{
@Autowired
private SalaRepository salaRepository;

    @Override
    public void run(String... args) throws Exception {
    
        Sala sala1=new Sala(
            "Sala1","Primer Piso","300 Personas","Mañana"
        );
        salaRepository.save(sala1);
        Sala sala2 =new Sala("Sala2","Segundo Piso","250 Personas","Tarde");
        salaRepository.save(sala2);
    }
    
}
    

