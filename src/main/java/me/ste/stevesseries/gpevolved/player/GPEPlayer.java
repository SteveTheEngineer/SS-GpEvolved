package me.ste.stevesseries.gpevolved.player;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.Optional;
import java.util.UUID;

public class GPEPlayer {
    private UUID uuid;
    private BodyPart head, body, leftArm, rightArm, leftLeg, rightLeg;

    public GPEPlayer(OfflinePlayer p) {
        uuid = p.getUniqueId();

        head = new BodyPart(75, 5, (t) -> 5 * t.getLevel(), true);
        body = new BodyPart(150, 5, (t) -> 15 * t.getLevel(), true);
        rightArm = new BodyPart(100, 5, (t) -> 10 * t.getLevel(), true);
        leftArm = new BodyPart(100, 5, (t) -> 10 * t.getLevel(), true);
        rightLeg = new BodyPart(125, 5, (t) -> 20 * t.getLevel(), true);
        leftLeg = new BodyPart(125, 5, (t) -> 20 * t.getLevel(), true);
    }

    public OfflinePlayer getOfflinePlayer() {
        return Bukkit.getOfflinePlayer(uuid);
    }

    public BodyPart getHead() {
        return head;
    }
    public BodyPart getBody() {
        return body;
    }
    public BodyPart getLeftArm() {
        return leftArm;
    }
    public BodyPart getRightArm() {
        return rightArm;
    }
    public BodyPart getLeftLeg() {
        return leftLeg;
    }
    public BodyPart getRightLeg() {
        return rightLeg;
    }
    public boolean isOnline() {
        return getOfflinePlayer().isOnline();
    }

    public Optional<Player> getPlayer() {
        return Optional.ofNullable(getOfflinePlayer().getPlayer());
    }
}