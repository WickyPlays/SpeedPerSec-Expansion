package me.thienbao860.expansion.spsexpansion.type;

import me.thienbao860.expansion.spsexpansion.manager.SPS;
import me.thienbao860.expansion.spsexpansion.manager.SpeedType;

public class Speeding extends SPS {

    public Speeding() {
        super();
    }

    @Override
    public SpeedType getType() {
        return SpeedType.SPEEDING;
    }
    
}
