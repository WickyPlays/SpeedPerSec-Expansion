package me.thienbao860.expansion.spsexpansion.type;

import me.thienbao860.expansion.spsexpansion.manager.SPS;
import me.thienbao860.expansion.spsexpansion.manager.SpeedType;

public class BlockPlacing extends SPS {

    public BlockPlacing() {
        super();
    }

    @Override
    public SpeedType getType() {
        return SpeedType.BLOCK_PLACING;
    }

}
