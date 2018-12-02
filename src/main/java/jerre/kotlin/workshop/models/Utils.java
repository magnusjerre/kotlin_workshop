package jerre.kotlin.workshop.models;

import java.util.Collection;

public class Utils {
    public static Long getNextIdInSequence(Collection<? extends Id> ids) {
        return ids.stream().map(id -> id.getId()).max(Long::compare).orElse(1L);
    }
}
