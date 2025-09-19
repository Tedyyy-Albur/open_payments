package loop.open.payments.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "app_users")
@Data
@NoArgsConstructor
public class AppUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "is_deleted")
    private boolean isDeleted = false;

    @Column(name = "office_id")
    private Long officeId;

    @Column(name = "staff_id")
    private Long staffId;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "password", nullable = false, length = 100)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "firsttime_login_remaining")
    private boolean firsttimeLoginRemaining = true;

    @Column(name = "nonexpired")
    private boolean nonexpired = true;

    @Column(name = "nonlocked")
    private boolean nonlocked = true;

    @Column(name = "nonexpired_credentials")
    private boolean nonexpiredCredentials = true;

    @Column(name = "enabled")
    private boolean enabled = true;

    @Column(name = "last_time_password_updated")
    private LocalDate lastTimePasswordUpdated;

    @Column(name = "password_never_expires")
    private boolean passwordNeverExpires = false;

    @Column(name = "is_self_service_user")
    private boolean isSelfServiceUser = false;

    @Column(name = "cannot_change_password")
    private boolean cannotChangePassword = false;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    // Implementación de UserDetails
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.nonexpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.nonlocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.nonexpiredCredentials;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}