package loop.open.payments.repository;

import loop.open.payments.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<loop.open.payments.entity.Role, Long> {
    Optional<Role> findByName(String name);
    boolean existsByName(String name);
}