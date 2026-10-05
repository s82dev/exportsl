package net.smods.api;

/**
 * Implement this to add your own module to S.mods without touching or
 * rebuilding S.mods itself. Your module shows up as a tile under the
 * "🧩 Extern" tab in both GUIs, with the same toggle/persistence/HUD
 * behaviour as any built-in feature.
 *
 * HOW TO SHIP ONE:
 *  1. Copy this file (and {@link Canvas}) into your own, completely
 *     separate Java project - package name net.smods.api, same as here.
 *     No Minecraft, no Fabric, no Loom needed on your end; this file has
 *     zero dependencies beyond plain Java.
 *  2. Implement this interface.
 *  3. Add a file
 *       META-INF/services/net.smods.api.ExternalModule
 *     to your jar's resources, containing one line: the fully-qualified
 *     class name of your implementation. This is standard Java
 *     ServiceLoader wiring - it's how S.mods finds your class.
 *  4. Build a plain jar (just `javac` + `jar`, or any build tool you like).
 *  5. Drop the jar into:
 *       <.minecraft config dir>/smods/modules/
 *     (create the "modules" folder if it doesn't exist yet)
 *  6. Start Minecraft - your module appears under the Extern tab.
 *
 * See /external-modules/example-clock/ in the S.mods project for a
 * complete, working, copy-pasteable example.
 */
public interface ExternalModule {

    /** Stable, unique id - lowercase, no spaces, e.g. "my-cool-module". Used for save-state persistence. */
    String id();

    /** Shown on the tile, e.g. "My Cool Module". */
    String displayName();

    /** One emoji shown next to the name on the tile. Optional - default is a generic icon. */
    default String icon() {
        return "🧩";
    }

    /** Called once when the player turns this module on. */
    default void onEnable() {}

    /** Called once when the player turns this module off. */
    default void onDisable() {}

    /** Called every client tick while this module is enabled. */
    default void tick() {}

    /** Return true if this module wants to draw something on the HUD while enabled. */
    default boolean hasHudOverlay() {
        return false;
    }

    /**
     * Draw your HUD overlay here. Only called while enabled and only if
     * {@link #hasHudOverlay()} returns true. Use the given canvas instead
     * of any Minecraft rendering class directly - that's what keeps your
     * module buildable with plain Java, independent of game/mapping
     * version changes.
     */
    default void renderHud(Canvas canvas) {}

    /** Optional one-line status shown under the tile name, e.g. "42 ticks". */
    default String statusLine() {
        return null;
    }
}
