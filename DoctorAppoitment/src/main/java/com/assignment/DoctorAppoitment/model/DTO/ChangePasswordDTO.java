package com.assignment.DoctorAppoitment.model.DTO;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordDTO {
    private AuthenticationDTO authenticationDTO;

    @Pattern(regexp = "^.{8,}$",
            message = "Your password should be 8 length")
    private String password;
}
