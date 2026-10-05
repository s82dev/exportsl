package net.smods.api;

/**
 * A tiny drawing surface handed to {@link ExternalModule#renderHud}.
 * Deliberately NOT a Minecraft class - so your external module project
 * never needs Minecraft, Fabric, or Yarn mappings as a dependency, and
 * never breaks when those rename things between versions (which happens
 * a lot - ask the person who built S.mods...).
 *
 * All colors are 0xAARRGGBB ints - don't forget the alpha byte (0xFF for
 * fully opaque), or your drawing will be invisible.
 */
public interface Canvas {

    /** Current HUD width in GUI pixels. */
    int screenWidth();

    /** Current HUD height in GUI pixels. */
    int screenHeight();

    /** Draws text with a small drop shadow, top-left anchored at (x, y). */
    void text(String text, int x, int y, int argbColor);

    /** Draws a filled rectangle. */
    void rect(int x, int y, int width, int height, int argbColor);

    /** Draws a 1px rectangle outline. */
    void rectOutline(int x, int y, int width, int height, int argbColor);

    /** Width in pixels the given text would take if drawn with {@link #text}. */
    int textWidth(String text);
}
