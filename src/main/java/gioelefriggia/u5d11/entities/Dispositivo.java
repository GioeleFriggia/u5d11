package gioelefriggia.u5d11.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Entity
@Table(name = "dispositivi")
@Getter @Setter @NoArgsConstructor @ToString
public class Dispositivo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String tipo;

    @Column(nullable = false)
    private String stato;  // "disponibile", "assegnato", "in manutenzione", "dismesso"

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dipendente_id")
    private Dipendente dipendente;


    public Dispositivo(String tipo, String stato) {
        this.tipo = tipo;
        this.stato = stato;
    }
}
