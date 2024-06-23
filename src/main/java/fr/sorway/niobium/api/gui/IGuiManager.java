package fr.sorway.niobium.api.gui;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface IGuiManager {
    Map<UUID, GuiData> getGuis();
    List<UUID> getOpenedGui();
}
