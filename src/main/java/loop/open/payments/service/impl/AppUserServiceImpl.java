package loop.open.payments.service.impl;


import loop.open.payments.entity.AppUser;
import loop.open.payments.entity.Role;
import loop.open.payments.repository.AppUserRepository;
import loop.open.payments.repository.RoleRepository;
import loop.open.payments.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class AppUserServiceImpl implements AppUserService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Crea un nuevo usuario.
     * La lógica clave aquí es encriptar la contraseña y asignar un rol por defecto.
     * @param user El objeto AppUser con los datos del nuevo usuario.
     * @return El usuario guardado en la base de datos.
     */
    public AppUser registerUser(AppUser user) {

        // Asignar el rol de usuario por defecto ("ROLE_USER")
        // Esto asume que el rol "ROLE_USER" ya existe en la base de datos.
        //Role userRole = roleRepository.findByName("ROLE_USER")
          //      .orElseThrow(() -> new RuntimeException("Error: Rol no encontrado."));

        //user.setRoles(Collections.singleton(userRole));
        if (user.getUsername() == null || user.getUsername().isBlank()) {
            throw new RuntimeException("Username es requerido");
        }
        if (user.getEmail() == null || user.getEmail().isBlank()) {
            throw new RuntimeException("Email es requerido");
        }
        if (user.getPassword() == null || user.getPassword().isBlank()) {
            throw new RuntimeException("Password es requerido");
        }
        if (appUserRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Username ya existe");
        }
        if (appUserRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email ya existe");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role userRole  = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Error: Rol no encontrado."));
       // Role adminRole = roleRepository.findByName("ROLE_ADMIN")
       //         .orElseThrow(() -> new RuntimeException("Error: Rol no encontrado."));
        user.getRoles().clear();
        user.getRoles().add(userRole);
        //user.getRoles().add(adminRole);

        return appUserRepository.save(user);

    }

    public List<AppUser> getAllUsers() {
        return appUserRepository.findAll();
    }

    public Optional<AppUser> getUserById(Long id) {
        return appUserRepository.findById(id);
    }

    public Optional<AppUser> getUserByUsername(String username) {
        return appUserRepository.findByUsername(username);
    }

    public void deleteUser(Long id) {
        appUserRepository.deleteById(id);
    }

    // Aquí podrías añadir un método para actualizar usuarios, manejar roles, etc.
}
