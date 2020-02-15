package me.thienbao860.expansion.spsexpansion.type;

import me.thienbao860.expansion.spsexpansion.manager.SPS;
import me.thienbao860.expansion.spsexpansion.manager.SpeedType;

public class ExpCollecting extends SPS {

    public ExpCollecting() {
        super();
    }

    @Override
    public SpeedType getType() {
        return SpeedType.EXP_COLLECTED;
    }
}
