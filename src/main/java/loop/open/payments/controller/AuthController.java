package loop.open.payments.controller;


import loop.open.payments.entity.AppUser;
import loop.open.payments.model.enums.AuthRequest;
import loop.open.payments.model.enums.AuthResponse;
//import loop.open.payments.security.JwtUtilService;
import loop.open.payments.security.JwtUtilService;
import loop.open.payments.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtUtilService jwtUtilService;
    @Autowired
    private AppUserService appUserService;


    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthRequest authRequest) throws Exception {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
        final String jwt = jwtUtilService.generateToken(userDetails);

        return ResponseEntity.ok(new AuthResponse(jwt));
    }
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody AppUser user) {
        try {
            AppUser registeredUser = appUserService.registerUser(user);
            return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            // Manejo básico de errores, por si el rol no existe o el usuario ya está registrado.
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}