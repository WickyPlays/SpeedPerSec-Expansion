package me.thienbao860.expansion.spsexpansion.type;

import me.thienbao860.expansion.spsexpansion.manager.SPS;
import me.thienbao860.expansion.spsexpansion.manager.SpeedType;

public class EntityDamaging extends SPS {

    public EntityDamaging() {
        super();
    }

    @Override
    public SpeedType getType() {
        return SpeedType.ENTITY_DAMAGING;
    }
    
}
