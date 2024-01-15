package edu.hust.it4409.web.security;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RequiredArgsConstructor
@RestController
class SecurityController {
    private final UserDetailsManager userDetailsService;
    @GetMapping("/user/current")
    public Object user(@CurrentSecurityContext(expression = "authentication.principal") Object principal) {
        return principal;
    }

    @PostMapping("/user/new")
    public ResponseEntity<?> createNewUser(@RequestBody UserDetails details) {
        // userDetailsService.createUser(details);
        return ResponseEntity.created(URI.create("/user/TODO")).build();
    }
}
