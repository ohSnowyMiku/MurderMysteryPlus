package ohSnowyMiku.MurderMysteryHelper.mixin;

import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Scoreboard.class)
public class MixinScoreboard {
    @Inject(method = "removeTeam", at = @At("HEAD"), cancellable = true)
    private void onRemoveTeam(ScorePlayerTeam team, CallbackInfo ci) {
        if (team == null) {
            // 防止 NullPointerException
            //System.out.println("[MixinScoreboard] 忽略了一个 null 的队伍移除请求");
            ci.cancel();
        }
    }

    @Inject(method = "removeObjective", at = @At("HEAD"), cancellable = true)
    private void onRemoveObjective(ScoreObjective objective, CallbackInfo ci) {
        if (objective == null) {
            // 直接取消执行，避免 NPE
            ci.cancel();
        }
    }
}
