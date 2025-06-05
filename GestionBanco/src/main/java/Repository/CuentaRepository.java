package Repository;
import Entity.Cliente;
import Entity.Cuenta;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Integer> {
	List<Cuenta> findByCliente(Cliente cliente);
	Cuenta findByNumerocuenta(int numerocuenta);
}
