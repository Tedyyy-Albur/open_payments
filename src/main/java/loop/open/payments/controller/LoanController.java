package loop.open.payments.controller;


import loop.open.payments.entity.Loan;
import loop.open.payments.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @GetMapping
    public List<Loan> getAllLoans() {
        return loanService.getAllLoans();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Loan> getLoanById(@PathVariable Long id) {
        return loanService.getLoanById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Loan createLoan(@RequestBody Loan loan) {
        return loanService.saveLoan(loan);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLoan(@PathVariable Long id){
        loanService.deleteLoan(id);
        return ResponseEntity.noContent().build();
    }

    // Implementa los métodos PUT/PATCH para actualizar según necesites.
}
// NOTA: Deberías crear controladores similares para AppUser y Role.
