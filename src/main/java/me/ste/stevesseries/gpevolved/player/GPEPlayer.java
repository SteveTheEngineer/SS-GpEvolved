package me.ste.stevesseries.gpevolved.player;

import me.ste.stevesseries.corebase.registry.Registry;
import me.ste.stevesseries.corebase.registry.RegistryManager;
import me.ste.stevesseries.gpevolved.GpEvolved;
import me.ste.stevesseries.gpevolved.player.disease.Disease;
import me.ste.stevesseries.gpevolved.player.disease.ExistingDisease;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;

import java.util.*;

public class GPEPlayer {
    private static final Registry DISEASE_REGISTRY = RegistryManager.getInstance().getRegistryExceptional(new NamespacedKey(GpEvolved.getPlugin(GpEvolved.class), "disease"));

    public static Map<UUID, GPEPlayer> players = new HashMap<>();

    public static GPEPlayer getGPEPlayer(Player p) {
        return players.get(p.getUniqueId());
    }

    private UUID uuid;
    private BodyPart head, body, leftArm, rightArm, leftLeg, rightLeg;
    private Set<ExistingDisease> diseases;

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

    public Optional<ExistingDisease> getDisease(NamespacedKey disease) {
        for(ExistingDisease existingDisease : diseases) {
            if(DISEASE_REGISTRY.getEntry(disease).get().equals(existingDisease.getDisease())) {
                return Optional.of(existingDisease);
            }
        }
        return Optional.empty();
    }

    public boolean disease(NamespacedKey disease, long duration) {
        Optional<ExistingDisease> diseaseOptional = getDisease(disease);
        if(diseaseOptional.isPresent()) {
            ExistingDisease existingDisease = diseaseOptional.get();
            existingDisease.setDuration(existingDisease.getDuration() + duration);
            return true;
        } else {
            if(duration >= 0) {
                diseases.add(new ExistingDisease((Disease) DISEASE_REGISTRY.getEntry(disease).get(), duration, 0));
            }
            return false;
        }
    }

    public void undisease(NamespacedKey disease) {
        diseases.removeIf(existingDisease -> DISEASE_REGISTRY.getEntry(disease).get().equals(existingDisease.getDisease()));
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(uuid);
    }
}