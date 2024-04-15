package gioelefriggia.u5d11.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import gioelefriggia.u5d11.entities.Dispositivo;
import gioelefriggia.u5d11.entities.Dipendente;
import gioelefriggia.u5d11.services.DeviceService;
import gioelefriggia.u5d11.services.EmployeeService;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/dispositivi")
public class DispositivoController {

    private final DeviceService deviceService;
    private final EmployeeService employeeService;

    @Autowired
    public DispositivoController(DeviceService deviceService, EmployeeService employeeService) {
        this.deviceService = deviceService;
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<Dispositivo>> getAllDevices() {
        List<Dispositivo> dispositivi = deviceService.findAllDevices();
        return ResponseEntity.ok(dispositivi);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dispositivo> getDeviceById(@PathVariable UUID id) {
        return deviceService.findDeviceById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Dispositivo> createDevice(@RequestBody Dispositivo dispositivo) {
        Dispositivo savedDispositivo = deviceService.saveDevice(dispositivo);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDispositivo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dispositivo> updateDevice(@PathVariable UUID id, @RequestBody Dispositivo dispositivo) {
        return deviceService.findDeviceById(id)
                .map(existingDispositivo -> {
                    dispositivo.setId(existingDispositivo.getId());
                    Dispositivo updatedDispositivo = deviceService.saveDevice(dispositivo);
                    return ResponseEntity.ok(updatedDispositivo);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDevice(@PathVariable UUID id) {
        deviceService.deleteDevice(id);
        return ResponseEntity.noContent().build();
    }

    // Nuovo endpoint per assegnare un dispositivo a un dipendente
    @PutMapping("/{dispositivoId}/assegna-dipendente/{dipendenteId}")
    public ResponseEntity<Dispositivo> assegnaDispositivoADipendente(@PathVariable UUID dispositivoId, @PathVariable UUID dipendenteId) {
        Dispositivo dispositivo = deviceService.findDeviceById(dispositivoId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Dispositivo non trovato"));
        Dipendente dipendente = employeeService.findEmployeeById(dipendenteId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Dipendente non trovato"));

        dispositivo.setDipendente(dipendente);
        dispositivo = deviceService.saveDevice(dispositivo);
        return ResponseEntity.ok(dispositivo);
    }
}