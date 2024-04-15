package gioelefriggia.u5d11.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import gioelefriggia.u5d11.entities.Dipendente;
import gioelefriggia.u5d11.payloads.DipendenteDTO;
import gioelefriggia.u5d11.services.EmployeeService;

import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/dipendenti")
public class DipendenteController {

    private final EmployeeService employeeService;

    @Autowired
    public DipendenteController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<Dipendente>> getAllEmployees() {
        List<Dipendente> dipendenti = employeeService.findAllEmployees();
        return ResponseEntity.ok(dipendenti);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dipendente> getEmployeeById(@PathVariable UUID id) {
        return employeeService.findEmployeeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Dipendente> createEmployee(@Valid @RequestBody DipendenteDTO dipendenteDTO) {
        Dipendente dipendente = convertiDtoADipendente(dipendenteDTO);
        Dipendente savedDipendente = employeeService.saveEmployee(dipendente);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDipendente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dipendente> updateEmployee(@PathVariable UUID id, @Valid @RequestBody DipendenteDTO dipendenteDTO) {
        Dipendente existingDipendente = employeeService.findEmployeeById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Dipendente non trovato"));
        Dipendente dipendenteToUpdate = convertiDtoADipendente(dipendenteDTO);
        dipendenteToUpdate.setId(existingDipendente.getId()); // Preserva l'ID originale
        Dipendente updatedDipendente = employeeService.saveEmployee(dipendenteToUpdate);
        return ResponseEntity.ok(updatedDipendente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable UUID id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/upload-immagine")
    public ResponseEntity<String> uploadImmagineProfilo(@PathVariable UUID id, @RequestParam("file") MultipartFile file) {
        String urlImmagazzinato = ""; // = Logica per ottenere l'URL dell'immagine caricata

        Dipendente dipendente = employeeService.findEmployeeById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Dipendente non trovato"));
        dipendente.setImmagineUrl(urlImmagazzinato);
        employeeService.saveEmployee(dipendente);

        return ResponseEntity.ok("Immagine caricata con successo: " + urlImmagazzinato);
    }


    private Dipendente convertiDtoADipendente(DipendenteDTO dto) {
        Dipendente dipendente = new Dipendente();
        dipendente.setUsername(dto.username());
        dipendente.setNome(dto.nome());
        dipendente.setCognome(dto.cognome());
        dipendente.setEmail(dto.email());
        return dipendente;
    }
}