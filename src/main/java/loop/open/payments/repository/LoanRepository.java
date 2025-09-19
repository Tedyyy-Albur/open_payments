package loop.open.payments.repository;


import loop.open.payments.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<loop.open.payments.entity.Loan, Long> {}