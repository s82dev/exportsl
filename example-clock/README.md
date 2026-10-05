# example-clock

A complete, working S.mods external module. Shows the system clock top-center
on the HUD while enabled. Use this as the template for your own modules.

## Build it

**Option A - plain `javac`/`jar` (no Gradle, no internet needed):**

```powershell
cd external-modules\example-clock
mkdir out
javac -d out (Get-ChildItem -Recurse -Filter *.java src\main\java | % { $_.FullName })
Copy-Item -Recurse src\main\resources\* out\
cd out
jar cf ..\example-clock.jar .
cd ..
```

**Option B - Gradle (if you have it, e.g. via `choco install gradle`):**

```powershell
cd external-modules\example-clock
gradle build
```

The jar will be at `build\libs\example-clock-1.0.0.jar`.

## Install it

Copy the jar into:

```
<Minecraft config folder>\smods\modules\
```

(same folder S.mods itself uses for `smods.json` — create the `modules`
subfolder if it's not there yet). Start Minecraft; "🕒 Clock" shows up as a
tile under the **🧩 Extern** tab in both GUIs.

## Make your own module

1. Copy this whole `example-clock` folder, rename it.
2. Rename/rewrite `ClockModule.java` (and move it out of `com.example.smodsmodule`
   into your own package if you like — doesn't matter, as long as it's not
   `net.smods.*`, which is reserved for the API files).
3. Update `src/main/resources/META-INF/services/net.smods.api.ExternalModule`
   to contain your new class's fully-qualified name (one line, no package
   wildcards).
4. Implement whatever you want in `onEnable`/`onDisable`/`tick`/`renderHud` —
   see `net/smods/api/ExternalModule.java` and `Canvas.java` for the full,
   documented API surface. It's deliberately small and Minecraft-independent,
   so it won't break when a Minecraft update renames things (which, if you've
   seen the rest of this project's history, happens *constantly*).
5. Build and drop the jar in the `modules` folder as above.

A broken module (throws an exception, missing service file, etc.) is skipped
with a warning in the Minecraft log — it will never crash the game or stop
other modules/features from working.
