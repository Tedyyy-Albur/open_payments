package loop.open.payments.model.enums;

import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private String password;
}