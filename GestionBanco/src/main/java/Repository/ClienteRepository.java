package Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Entity.Cliente;
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
    Cliente findByDni(String dni);
}
