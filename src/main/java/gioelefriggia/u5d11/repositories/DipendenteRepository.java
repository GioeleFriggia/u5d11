package gioelefriggia.u5d11.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import gioelefriggia.u5d11.entities.Dipendente;
import java.util.UUID;

public interface DipendenteRepository extends JpaRepository<Dipendente, UUID> {

}