package sindarin.fireproofitems;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import org.apache.commons.lang3.tuple.Pair;

public class ModConfig {
    public static class Server {
        public final ForgeConfigSpec.BooleanValue isWhitelist;

        Server(ForgeConfigSpec.Builder builder) {
            builder.comment("Server configuration settings").push("server");
            this.isWhitelist = builder
                    .comment("Whether the tag should be treated as a whitelist or as a blacklist",
                            "True  -> Only items with the tag become invulnerable",
                            "False -> Only items without the tag become invulnerable")
                    .define("isTagWhitelist", true);
        }
    }

    static final ForgeConfigSpec serverSpec;
    public static final ModConfig.Server SERVER;
    static {
        final Pair<Server, ForgeConfigSpec> serverPair = new ForgeConfigSpec.Builder().configure(ModConfig.Server::new);
        serverSpec = serverPair.getRight();
        SERVER = serverPair.getLeft();
    }
}
