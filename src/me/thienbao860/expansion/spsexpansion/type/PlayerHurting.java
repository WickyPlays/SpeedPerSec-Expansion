package me.thienbao860.expansion.spsexpansion.type;

import me.thienbao860.expansion.spsexpansion.manager.SPS;
import me.thienbao860.expansion.spsexpansion.manager.SpeedType;

public class PlayerHurting extends SPS {

    public PlayerHurting() {
        super();
    }

    @Override
    public SpeedType getType() {
        return SpeedType.PLAYER_HURT;
    }
}
