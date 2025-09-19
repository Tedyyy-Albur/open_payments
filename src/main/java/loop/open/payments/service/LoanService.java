package loop.open.payments.service;

import loop.open.payments.entity.Loan;

import java.util.List;
import java.util.Optional;

public interface LoanService {
    public List<Loan> getAllLoans();
    public Optional<Loan> getLoanById(Long id);
    public Loan saveLoan(Loan loan);
    public void deleteLoan(Long id);

}
