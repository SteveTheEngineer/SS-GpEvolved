package me.ste.stevesseries.gpevolved.player.disease;

import com.google.common.base.Preconditions;

public class ExistingDisease {
    private Disease disease;
    private long duration, lastedFor;

    public ExistingDisease(Disease disease, long duration, long lastedFor) {
        this.disease = disease;
        this.duration = duration;
        this.lastedFor = lastedFor;
    }

    public Disease getDisease() {
        return disease;
    }

    public long getLastedFor() {
        return lastedFor;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
        if(lastedFor > duration) {
            lastedFor = duration;
        }
    }

    public void setLastedFor(long lastedFor) {
        Preconditions.checkState(lastedFor > duration, "lastedFor > duration");
        this.lastedFor = lastedFor;
    }
}