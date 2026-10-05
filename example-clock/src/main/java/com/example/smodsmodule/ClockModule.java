package com.example.smodsmodule;

import net.smods.api.Canvas;
import net.smods.api.ExternalModule;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Example S.mods external module: shows the real-world (system) clock
 * top-center on the HUD while enabled. Nothing Minecraft-specific here -
 * this whole class is plain Java.
 *
 * This is the file to copy/rename/gut when starting your own module.
 * The three things that actually matter are:
 *   1. implement net.smods.api.ExternalModule
 *   2. give it a stable, unique id()
 *   3. list this class's fully-qualified name in
 *      src/main/resources/META-INF/services/net.smods.api.ExternalModule
 */
public class ClockModule implements ExternalModule {

    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("HH:mm:ss");

    @Override
    public String id() {
        return "example-clock";
    }

    @Override
    public String displayName() {
        return "Clock";
    }

    @Override
    public String icon() {
        return "🕒";
    }

    @Override
    public void onEnable() {
        System.out.println("[example-clock] enabled");
    }

    @Override
    public void onDisable() {
        System.out.println("[example-clock] disabled");
    }

    @Override
    public boolean hasHudOverlay() {
        return true;
    }

    @Override
    public void renderHud(Canvas canvas) {
        String text = LocalTime.now().format(FORMAT);
        int width = canvas.textWidth(text);
        int x = (canvas.screenWidth() - width) / 2;
        canvas.rect(x - 4, 4, width + 8, 14, 0x80000000);
        canvas.text(text, x, 6, 0xFFFFFFFF);
    }

    @Override
    public String statusLine() {
        return LocalTime.now().format(FORMAT);
    }
}
