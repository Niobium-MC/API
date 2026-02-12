package fr.sorway.niobium.api.message;

import fr.sorway.niobium.api.NiobiumAPI;
import fr.sorway.niobium.api.data.accounts.IPlayerAccount;
import fr.sorway.niobium.api.data.ranks.IRank;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.ServerOperator;

import java.util.List;

/**
 * Utilitaire statique permettant d’envoyer des messages formatés aux joueurs et aux entités de commande (CommandSender).
 * <p>
 * Cette classe centralise l'envoi de messages avec :
 * <ul>
 *     <li>Un préfixe standardisé {@link #PREFIX_COMPONENT}</li>
 *     <li>Le support de messages au format {@link Component}</li>
 *     <li>La conversion automatique des chaînes de caractères formatées via {@link MessageParser#parse(String)}</li>
 *     <li>Des méthodes dédiées pour les erreurs et les messages opérateurs</li>
 * </ul>
 * </p>
 * <p>
 * <b>Important :</b> cette classe ne peut pas être instanciée.
 * </p>
 */
public final class MessageHelper {

    /**
     * Constructeur privé empêchant l’instanciation de cette classe utilitaire.
     */
    private MessageHelper() {
        throw new UnsupportedOperationException("Cette classe ne peut pas être instanciée.");
    }

    /**
     * Préfixe par défaut utilisé pour tous les messages envoyés via cette classe.
     * Formaté en utilisant MiniMessage (couleur personnalisée et texte en gras).
     */
    public static final Component PREFIX_COMPONENT = MiniMessage.miniMessage().deserialize("<#74b9ff><bold>Niobium</bold> <gray>» <reset><white>");

    /**
     * Récupère le nom complet du joueur, incluant son préfixe, son suffixe, et sa couleur de rang.
     *
     * @param api L'instance de NiobiumAPI utilisée pour accéder aux données des comptes.
     * @param player L'instance du joueur dont on veut récupérer le nom complet.
     * @return Une chaîne de caractères représentant le nom complet du joueur avec son rang.
     */

    public static Component playerName(NiobiumAPI api, Player player) {
        IPlayerAccount account = api.getAccountManager().getAccount(player);
        IRank rank = account.playerRank().activeRank().rank();;

        Component component = Component.empty();

        // Prefix
        if (rank.prefix() != null && !rank.prefix().isEmpty())
            component = component.append(MessageParser.parse(rank.prefix() + " "));

        // Nom du joueur
        component = component.append(Component.text(player.getName(), rank.tabColor()));

        // Suffix
        if (rank.suffix() != null && !rank.suffix().isEmpty())
            component = component.append(MessageParser.parse(" " + rank.suffix()));

        return component;
    }

    /**
     * Envoie un message formaté à tous les opérateurs connectés.
     * Le message est parsé automatiquement depuis la chaîne donnée.
     *
     * @param message Le message au format brut contenant des codes de style.
     * @see MessageParser#parse(String)
     * @see #sendOperators(Component)
     */
    public static void sendOperators(String message) {
        sendOperators(MessageParser.parse(message));
    }

    /**
     * Envoie un message formaté à tous les opérateurs connectés.
     *
     * @param message Le message déjà transformé en {@link Component}.
     */
    public static void sendOperators(Component message) {
        Bukkit.getOnlinePlayers().stream()
                .filter(ServerOperator::isOp)
                .forEach(player -> player.sendMessage(PREFIX_COMPONENT.append(message)));
    }

    /**
     * Diffuse un message à tous les joueurs connectés.
     * Le message est parsé automatiquement depuis la chaîne donnée.
     *
     * @param message Le message brut contenant les codes de style.
     * @see MessageParser#parse(String)
     * @see #broadcast(Component)
     */
    public static void broadcast(String message) {
        broadcast(MessageParser.parse(message));
    }

    /**
     * Diffuse un message à tous les joueurs connectés.
     *
     * @param message Le message au format {@link Component}.
     */
    public static void broadcast(Component message) {
        Bukkit.getOnlinePlayers()
                .forEach(player -> player.sendMessage(PREFIX_COMPONENT.append(message)));
    }

    /**
     * Envoie un message à un joueur donné, en parsant automatiquement la chaîne.
     *
     * @param player Le joueur ciblé.
     * @param message Le message brut contenant les codes de style.
     * @see MessageParser#parse(String)
     * @see #sendMessage(Player, Component)
     */
    public static void sendMessage(Player player, String message) {
        sendMessage(player, MessageParser.parse(message));
    }

    /**
     * Envoie un message à un joueur donné.
     *
     * @param player Le joueur ciblé.
     * @param message Le message au format {@link Component}.
     */
    public static void sendMessage(Player player, Component message) {
        player.sendMessage(PREFIX_COMPONENT.append(message));
    }

    /**
     * Envoie un message à un expéditeur de commande.
     *
     * @param sender L'expéditeur (joueur, console, etc.).
     * @param message Le message au format {@link Component}.
     */
    public static void sendMessage(CommandSender sender, Component message) {
        sender.sendMessage(PREFIX_COMPONENT.append(message));
    }

    /**
     * Envoie un message à un expéditeur de commande en parsant la chaîne automatiquement.
     *
     * @param sender L'expéditeur de la commande.
     * @param message Le message brut avec codes de style.
     * @see MessageParser#parse(String)
     */
    public static void sendMessage(CommandSender sender, String message) {
        sendMessage(sender, MessageParser.parse(message));
    }

    /**
     * Envoie un message d'erreur à un joueur.
     * Le message sera toujours coloré en rouge (voir {@link MessageColor#ERROR_COLOR}).
     *
     * @param player Le joueur ciblé.
     * @param message Le message d'erreur au format {@link Component}.
     */
    public static void sendError(Player player, Component message) {
        message = message.color(MessageColor.ERROR_COLOR);
        player.sendMessage(PREFIX_COMPONENT.append(message));
    }

    /**
     * Envoie un message d'erreur à un joueur, après parsing.
     * Le message sera toujours coloré en rouge (voir {@link MessageColor#ERROR_COLOR}).
     *
     * @param player Le joueur ciblé.
     * @param message Le message brut contenant les codes de style.
     * @see MessageParser#parse(String)
     */
    public static void sendError(Player player, String message) {
        Component parsed = MessageParser.parse(message);
        sendError(player, PREFIX_COMPONENT.append(forceColor(parsed, MessageColor.ERROR_COLOR)));
    }

    /**
     * Envoie un message d'erreur à un expéditeur de commande.
     * Le message sera toujours coloré en rouge (voir {@link MessageColor#ERROR_COLOR}).
     *
     * @param sender L'expéditeur ciblé.
     * @param message Le message d'erreur au format {@link Component}.
     */
    public static void sendError(CommandSender sender, Component message) {
        message = message.color(MessageColor.ERROR_COLOR);
        sender.sendMessage(PREFIX_COMPONENT.append(message));
    }

    /**
     * Envoie un message d'erreur à un expéditeur de commande, après parsing.
     * Le message sera toujours coloré en rouge (voir {@link MessageColor#ERROR_COLOR}).
     *
     * @param sender L'expéditeur ciblé.
     * @param message Le message d'erreur brut contenant les codes de style.
     * @see MessageParser#parse(String)
     */
    public static void sendError(CommandSender sender, String message) {
        Component parsed = MessageParser.parse(message);
        sendError(sender, PREFIX_COMPONENT.append(forceColor(parsed, MessageColor.ERROR_COLOR)));
    }

    /**
     * Applique une couleur donnée à un {@link Component} et tous ses enfants récursivement.
     *
     * @param component Le composant à recolorer.
     * @param color La couleur à appliquer.
     * @return Un nouveau composant avec la couleur forcée.
     */
    private static Component forceColor(Component component, TextColor color) {
        Component recolored = component.style(component.style().color(color));

        List<Component> newChildren = component.children().stream()
                .map(child -> forceColor(child, color))
                .toList();

        return recolored.children(newChildren);
    }

    /**
     * Compare un composant Minecraft {@link net.kyori.adventure.text.Component} à une chaîne de caractères
     * en ignorant la casse et les crochets qui peuvent entourer le texte.
     * <p>
     * Le composant est converti en texte brut à l’aide de {@link net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer},
     * puis nettoyé des caractères `[` et `]` avant comparaison.
     *
     * @param component le composant Adventure à comparer
     * @param target    la chaîne de caractères cible (sans mise en forme)
     * @return {@code true} si le contenu brut du composant est égal (sans casse) à la cible, sinon {@code false}
     */
    public static boolean equalsComponent(Component component, String target) {
        return PlainTextComponentSerializer
                .plainText()
                .serialize(component)
                .replace("[", "").replace("]", "")
                .equalsIgnoreCase(target);
    }

    /**
     * Convertit un {@link Component} en une {@link String} de texte brut.
     * <p>
     * La conversion est effectuée en utilisant le {@link PlainTextComponentSerializer},
     * qui extrait le texte brut du composant. Ensuite, tous les crochets ouvrants "["
     * et fermants "]" sont supprimés de la chaîne résultante.
     * </p>
     *
     * @param component le {@link Component} à convertir en texte brut
     * @return une chaîne de texte brut représentant le composant, sans crochets "[" ni "]"
     */
    public static String convertComponent(Component component) {
        return PlainTextComponentSerializer
                .plainText()
                .serialize(component)
                .replace("[", "").replace("]", "");
    }
}

