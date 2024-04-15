package gioelefriggia.u5d11.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "dipendenti")
@Getter @Setter @NoArgsConstructor @ToString
public class Dipendente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cognome;

    @Column(nullable = false, unique = true)
    private String email;

    // NUOVO CAMPO per l'URL dell'immagine del profilo
    @Column(name = "immagine_url")
    private String immagineUrl;

    @OneToMany(mappedBy = "dipendente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Dispositivo> dispositivi;

    public Dipendente(String username, String nome, String cognome, String email) {
        this.username = username;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
    }
}
