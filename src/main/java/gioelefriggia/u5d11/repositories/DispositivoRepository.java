package gioelefriggia.u5d11.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import gioelefriggia.u5d11.entities.Dispositivo;
import java.util.UUID;

public interface DispositivoRepository extends JpaRepository<Dispositivo, UUID> {

}