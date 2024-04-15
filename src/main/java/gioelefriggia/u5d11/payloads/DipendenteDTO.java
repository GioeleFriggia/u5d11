package gioelefriggia.u5d11.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record DipendenteDTO(
        @Size(min = 5, max = 20, message = "L'username deve essere compreso tra i 5 e i 20 caratteri")
        String username,
        @Size(min = 2, max = 30, message = "Il nome deve essere compreso tra i 2 e i 30 caratteri")
        String nome,
        @Size(min = 2, max = 30, message = "Il cognome deve essere compreso tra i 2 e i 30 caratteri")
        String cognome,
        @Email(message = "L'email inserita non è valida")
        String email
) {
}
