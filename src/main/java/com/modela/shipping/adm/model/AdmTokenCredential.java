package com.modela.shipping.adm.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.modela.shipping.adm.util.ShippingConstant;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * The persistent class for the adm_token_credential database table.
 */

@Entity
@Table(name = "adm_token_credential")
@Data
public class AdmTokenCredential {

    @Id
    @SequenceGenerator(name = "tokenCredentialIdGenerator", sequenceName = "SEQ_ADM_TOKEN_CREDENTIAL", initialValue = 5000, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tokenCredentialIdGenerator")
    @Column(name = "token_credential_id")
    private Long tokenCredentialId;

    @Column(name = "token")
    @Size(max = 1024)
    private String token;

    @Column(name = "entry_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = ShippingConstant.DATETIME_FORMAT)
    private LocalDateTime entryDate;

    @Column(name = "expiration_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = ShippingConstant.DATETIME_FORMAT)
    private LocalDateTime expirationDate;

    @Column(name = "user_agent")
    @Size(max = 250)
    private String userAgent;

    @Column(name = "user_id")
    private Long userId;

    public AdmTokenCredential(String token, LocalDateTime entryDate, LocalDateTime expirationDate, String userAgent, Long userId) {
        this.token = token;
        this.entryDate = entryDate;
        this.expirationDate = expirationDate;
        this.userAgent = userAgent;
        this.userId = userId;
    }

    public AdmTokenCredential() {

    }
}
