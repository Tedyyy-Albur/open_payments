package loop.open.payments.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import loop.open.payments.model.enums.LoanType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "loans")
@Data
@NoArgsConstructor
public class Loan {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "account_no", unique = true)
        private String accountNo;

        @Column(name = "external_id", unique = true)
        private String externalId;

        @Column(name = "client_id")
        private Long clientId;

        @Column(name = "group_id")
        private Long groupId;

        @Column(name = "glim_id")
        private Long glimId;

        @Column(name = "product_id")
        private Long productId;

        @Column(name = "fund_id")
        private Long fundId;

        @Column(name = "loan_officer_id")
        private Long loanOfficerId;

        @Column(name = "loanpurpose_cv_id")
        private Long loanpurposeCvId;

        // Campos monetarios con BigDecimal para precisión
        @Column(name = "principal_amount_proposed", precision = 19, scale = 6)
        private BigDecimal principalAmountProposed;

        @Column(name = "principal_amount", precision = 19, scale = 6)
        private BigDecimal principalAmount;

        @Column(name = "approved_principal", precision = 19, scale = 6)
        private BigDecimal approvedPrincipal;

        @Column(name = "net_disbursal_amount", precision = 19, scale = 6)
        private BigDecimal netDisbursalAmount;

        // Fechas
        @Column(name = "submittedon_date")
        private LocalDate submittedonDate;

        @Column(name = "approvedon_date")
        private LocalDate approvedonDate;

        @Column(name = "expected_disbursedon_date")
        private LocalDate expectedDisbursedonDate;

        @Column(name = "disbursedon_date")
        private LocalDate disbursedonDate;

        @Column(name = "maturedon_date")
        private LocalDate maturedonDate;

        @Column(name = "closedon_date")
        private LocalDate closedonDate;

        // Booleans
        @Column(name = "is_floating_interest_rate")
        private Boolean isFloatingInterestRate;

        @Column(name = "is_npa")
        private Boolean isNpa;

        @Column(name = "is_topup")
        private Boolean isTopup;

        @Column(name = "is_fraud")
        private Boolean isFraud;

        // Enums
        @Enumerated(EnumType.STRING)
        @Column(name = "loan_type_enum")
        private LoanType loanTypeEnum;

        // Otros campos
        @Column(name = "term_frequency")
        private Integer termFrequency;

        @Column(name = "number_of_repayments")
        private Integer numberOfRepayments;

        @Column(name = "approvedon_userid")
        private Long approvedonUserid;

        @Column(name = "disbursedon_userid")
        private Long disbursedonUserid;

        // Timestamps
        @Column(name = "created_on_utc")
        private LocalDateTime createdOnUtc;

        @Column(name = "last_modified_on_utc")
        private LocalDateTime lastModifiedOnUtc;

    // ¡AÑADE AQUÍ EL RESTO DE LOS CAMPOS SOLICITADOS!
}