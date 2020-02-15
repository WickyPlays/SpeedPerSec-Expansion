package me.thienbao860.expansion.spsexpansion.type;

import me.thienbao860.expansion.spsexpansion.manager.SPS;
import me.thienbao860.expansion.spsexpansion.manager.SpeedType;

public class BlockBreaking extends SPS {

    public BlockBreaking() {
        super();
    }

    @Override
    public SpeedType getType() {
        return SpeedType.BLOCK_BREAKING;
    }

}
