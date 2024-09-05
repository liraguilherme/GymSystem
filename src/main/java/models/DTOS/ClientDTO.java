package models.DTOS;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public record ClientDTO(String name, String email, String cpf, String dateOfBirth, Integer idPlan, String phoneNumber ) {
}
