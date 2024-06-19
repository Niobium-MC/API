<div align="center">
  <img src="https://media.discordapp.net/attachments/1126983627700457533/1252599705179783168/logo-2.png?ex=667376a1&is=66722521&hm=c1f20c90bf74e6269c4aeb266936fd8241763b54f7eee158e876c310fe954cd1&=&format=webp&quality=lossless"/>

  ## Niobium-MC - Nionium-API
</div>

---

## Informations Générales

- **Nom :** Nionium-API
- **Type :** Infrastructure
- **Développeur(s) :** Sorway

## Description de l'API

L'API Nionium gère plusieurs aspects critiques, notamment :

- **Gestion des comptes :** Interface `IPlayerAccount`
- **Gestion des grades :** Interface `IRank`
- **Gestion des bases de données :** Interface `IDatabaseManager`

### Interfaces Clés

#### Interface `NiobiumAPI`

```java
public interface NiobiumAPI {

    /**
     * Récupère l'instance du plugin.
     *
     * @return l'instance du plugin
     */
    JavaPlugin getPlugin();

    /**
     * Récupère le gestionnaire de commandes.
     *
     * @return le gestionnaire de commandes
     */
    ICommandManager getCommandManager();

    /**
     * Récupère le gestionnaire de comptes.
     *
     * @return le gestionnaire de comptes
     */
    IAccountManager getAccountManager();

    /**
     * Récupère le gestionnaire de grades.
     *
     * @return le gestionnaire de grades
     */
    IRankManager getRankManager();

    /**
     * Récupère le gestionnaire de base de données.
     *
     * @return le gestionnaire de base de données
     */
    IDatabaseManager getDatabaseManager();

    /**
     * Enregistre un message avec le niveau de log spécifié.
     *
     * @param level le niveau de log
     * @param message le message à enregistrer
     */
    default void log(Level level, String message) {
        getPlugin().getLogger().log(level, message);
    }
}


