package gioelefriggia.u5d11.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record DispositivoDTO(
        @NotEmpty(message = "ATTENZIONE! Il tipo è obbligatorio")
        @Size(min = 3, max = 40, message = "Il tipo deve essere compreso tra i 3 e i 40 caratteri")
        String tipo,
        @NotEmpty(message = "ATTENZIONE! Lo stato è obbligatorio")
        @Size(min = 3, max = 20, message = "Lo stato deve essere compreso tra i 3 e i 20 caratteri")
        String stato,
        UUID dipendenteId
) {
}