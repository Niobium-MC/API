package fr.sorway.niobium.api.utils;

import java.util.Arrays;

/**
 * Représente les différents types de serveurs disponibles dans l'application.
 * <p>
 * Chaque constante correspond à une catégorie de serveur spécifique.
 * </p>
 */
public enum Servers {

    /**
     * Serveur commun (global).
     */
    COMMON,

    /**
     * Serveur de type survie.
     */
    SURVIE,

    /**
     * Serveur de type donjon.
     */
    DONJON,

    /**
     * Serveur de type ressource.
     */
    RESSOURCE,

    /**
     * Serveur principal (hub/lobby).
     */
    LOBBY;

    /**
     * Retourne l'instance {@link Servers} correspondant au nom fourni.
     * <p>
     * La recherche est insensible à la casse et ignore les espaces
     * en début et fin de chaîne.
     * </p>
     *
     * @param name le nom du serveur à rechercher
     * @return le serveur correspondant, ou {@code null} si aucun ne correspond
     * @throws IllegalArgumentException si {@code name} est {@code null}
     */
    public static Servers getServer(String name) {
        if (name == null)
            throw new IllegalArgumentException("Le nom du serveur ne peut pas être null.");
        return Arrays.stream(Servers.values())
                .filter(servers -> servers.name().equalsIgnoreCase(name.trim()))
                .findFirst()
                .orElse(null);
    }

    /**
     * Indique si le serveur est de type {@link #COMMON}.
     *
     * @return {@code true} si le serveur est {@code COMMON}, sinon {@code false}
     */
    public boolean isCommon() {
        return this == COMMON;
    }

    /**
     * Indique si le serveur est spécifique (différent de {@link #COMMON}).
     *
     * @return {@code true} si le serveur n'est pas {@code COMMON}, sinon {@code false}
     */
    public boolean isSpecific() {
        return this != COMMON;
    }
}
