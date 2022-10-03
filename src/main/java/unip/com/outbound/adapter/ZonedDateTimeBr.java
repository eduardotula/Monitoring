package unip.com.outbound.adapter;

import unip.com.outbound.port.ZonedDateTimeBrPort;

import javax.enterprise.context.ApplicationScoped;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@ApplicationScoped
public class ZonedDateTimeBr implements ZonedDateTimeBrPort {
    @Override
    public ZonedDateTime now() {
        return ZonedDateTime.now(getZoneId());
    }

    @Override
    public ZoneId getZoneId(){
        return ZoneId.of(ZoneId.SHORT_IDS.get("BET"));
    }

}
