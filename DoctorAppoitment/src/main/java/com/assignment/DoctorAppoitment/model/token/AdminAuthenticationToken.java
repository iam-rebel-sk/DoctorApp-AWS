package com.assignment.DoctorAppoitment.model.token;

import com.assignment.DoctorAppoitment.model.Admin;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AdminAuthenticationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tokenId;
    private String tokenValue;
    private LocalDateTime tokenCreationTime;

    @OneToOne
    @JoinColumn(name = "fk_admin_id")
    Admin admin;

    public AdminAuthenticationToken(Admin existingAdmin) {
        this.admin = existingAdmin;
        this.tokenValue = UUID.randomUUID().toString();
        this.tokenCreationTime = LocalDateTime.now();
    }
}
