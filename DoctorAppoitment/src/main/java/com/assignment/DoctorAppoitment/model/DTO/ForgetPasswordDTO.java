package com.assignment.DoctorAppoitment.model.DTO;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ForgetPasswordDTO {
    private EmailVerifierDTO userInfo;

    @Pattern(regexp = "^.{8,}$",
            message = "Your password should be 8 length")
    private String password;
}
