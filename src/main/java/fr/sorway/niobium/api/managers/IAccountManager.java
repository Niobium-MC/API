package fr.sorway.niobium.api.managers;

import fr.sorway.niobium.api.data.accounts.IPlayerAccount;
import org.bukkit.entity.Player;

import java.util.UUID;

public interface IAccountManager {
    void onLogin(Player player);
    void onLogout(Player player);
    void createAccount(Player player);
    void deleteAccount(Player player);
    void updateAccount(Player player);
    IPlayerAccount getAccount(UUID uuid);
    IPlayerAccount getAccount(Player player);
}
