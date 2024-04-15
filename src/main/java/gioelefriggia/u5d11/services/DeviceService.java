package gioelefriggia.u5d11.services;



import gioelefriggia.u5d11.entities.Dispositivo;
import gioelefriggia.u5d11.repositories.DispositivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DeviceService {

    private final DispositivoRepository deviceRepository;

    @Autowired
    public DeviceService(DispositivoRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    public List<Dispositivo> findAllDevices() {
        return deviceRepository.findAll();
    }

    public Optional<Dispositivo> findDeviceById(UUID id) {
        return deviceRepository.findById(id);
    }

    public Dispositivo saveDevice(Dispositivo dispositivo) {
        return deviceRepository.save(dispositivo);
    }

    public void deleteDevice(UUID id) {
        deviceRepository.deleteById(id);
    }

}