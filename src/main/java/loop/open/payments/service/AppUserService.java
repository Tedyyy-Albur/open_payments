package loop.open.payments.service;

import loop.open.payments.entity.AppUser;

import java.util.List;
import java.util.Optional;

public interface AppUserService {
    public AppUser registerUser(AppUser user);
    public List<AppUser> getAllUsers();
    public Optional<AppUser> getUserById(Long id);
    public Optional<AppUser> getUserByUsername(String username);
    public void deleteUser(Long id);

}
