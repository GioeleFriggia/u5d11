package gioelefriggia.u5d11.services;



import gioelefriggia.u5d11.entities.Dipendente;
import gioelefriggia.u5d11.repositories.DipendenteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeService {

    private final DipendenteRepository employeeRepository;

    @Autowired
    public EmployeeService(DipendenteRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Dipendente> findAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Dipendente> findEmployeeById(UUID id) {
        return employeeRepository.findById(id);
    }

    public Dipendente saveEmployee(Dipendente dipendente) {
        return employeeRepository.save(dipendente);
    }

    public void deleteEmployee(UUID id) {
        employeeRepository.deleteById(id);
    }

}