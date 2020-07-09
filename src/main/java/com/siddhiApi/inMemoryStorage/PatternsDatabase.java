package com.siddhiApi.inMemoryStorage;

import com.siddhiApi.entity.Pattern;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PatternsDatabase {
    private Set<Pattern> patterns;
    private static PatternsDatabase patternsDatabase;

    private PatternsDatabase() {
        this.patterns = new HashSet<>();
    }

    public static PatternsDatabase getPatternsDatabase(){
        if (patternsDatabase == null){
            patternsDatabase = new PatternsDatabase();
        }
        return patternsDatabase;
    }

    public Set<Pattern> getPatterns() {
        return patterns;
    }

    public void setPatterns(Set<Pattern> patterns) {
        this.patterns = patterns;
    }
}
