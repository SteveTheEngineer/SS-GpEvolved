package me.ste.stevesseries.gpevolved.player;

import me.ste.stevesseries.gpevolved.GpEvolved;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class GPEPlayer {
    public static Map<UUID, GPEPlayer> players = new HashMap<>();

    public static GPEPlayer getGPEPlayer(Player p) {
        return players.get(p.getUniqueId());
    }

    private UUID uuid;
    private BodyPart head, body, leftArm, rightArm, leftLeg, rightLeg;

    public GPEPlayer(Player p) {
        uuid = p.getUniqueId();

        head = new BodyPart(75, 5, (t) -> 5 * t.getLevel(), true);
        body = new BodyPart(150, 5, (t) -> 15 * t.getLevel(), true);
        rightArm = new BodyPart(100, 5, (t) -> 10 * t.getLevel(), true);
        leftArm = new BodyPart(100, 5, (t) -> 10 * t.getLevel(), true);
        rightLeg = new BodyPart(125, 5, (t) -> 20 * t.getLevel(), true);
        leftLeg = new BodyPart(125, 5, (t) -> 20 * t.getLevel(), true);
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

    public Player getPlayer() {
        return getPlayer();
    }
}