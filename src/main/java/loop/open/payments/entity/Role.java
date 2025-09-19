package loop.open.payments.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 100, unique = true, nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "is_disabled")
    private boolean isDisabled = false;
}