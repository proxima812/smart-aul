package net.smartaul.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/** Copy-once migration: leave the old config available for rollback. */
public final class ConfigMigration {
   private ConfigMigration() {}
   public static void copyLegacy(Path destination, Path legacy) throws IOException {
      if (!Files.exists(destination) && Files.isRegularFile(legacy)) {
         Files.createDirectories(destination.toAbsolutePath().getParent());
         Files.copy(legacy, destination);
      }
   }
}
