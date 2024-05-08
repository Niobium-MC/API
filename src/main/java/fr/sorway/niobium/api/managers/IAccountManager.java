package fr.sorway.niobium.api.managers;

import fr.sorway.niobium.api.data.accounts.PlayerAccount;
import org.bukkit.entity.Player;

import java.util.UUID;

public interface IAccountManager {
    void onLogin(Player player);
    void onLogout(Player player);
    void createAccount(Player player);
    void deleteAccount(Player player);
    void updateAccount(Player player);
    PlayerAccount getAccount(UUID uuid);
    PlayerAccount getAccount(Player player);
}
