package me.ste.stevesseries.gpevolved.player.disease;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import ru.ste.stevesseries.corebase.Identifier;

import java.util.Map;
import java.util.Set;

public class DiseaseManager {
    private DiseaseManager instance = new DiseaseManager();
    private Map<Identifier, Disease> registry = Maps.newHashMap();

    public DiseaseManager getInstance() {
        return instance;
    }

    public void registerDisease(Identifier id, Disease disease) {
        if(registry.containsKey(id)) {
            throw new IllegalArgumentException("Disease " + id.toString() + " is already registered");
        }
        registry.put(id, disease);
    }

    public Set<Identifier> getDiseases() {
        return Sets.newHashSet(registry.keySet());
    }
}