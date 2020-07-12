package com.siddhiApi.inMemoryStorage;

import com.siddhiApi.entity.Pattern;
import com.siddhiApi.exceptions.DuplicatedEntity;
import com.siddhiApi.exceptions.NotFoundException;

import java.util.*;

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

    public Pattern[] getPatterns() {
        Pattern[] patternsArray = new Pattern[patterns.size()];
        int i = 0;
        for (Pattern pattern : patterns){
            patternsArray[i] = pattern;
            ++i;
        }
        return patternsArray;
    }

    public void setPatterns(Set<Pattern> patterns) {
        this.patterns = patterns;
    }

    public void addPattern(Pattern pattern) throws DuplicatedEntity {
        if (patterns.contains(pattern)){
            throw new DuplicatedEntity("There is an existent pattern with this id already.");
        }
        patterns.add(pattern);
    }

    public Pattern getPattern(String id) throws NotFoundException {
        for (Pattern pattern: patterns){
            if (pattern.getPatternName().equals(id)){
                return pattern;
            }
        }
        throw new NotFoundException("There is no pattern with that name.");
    }
}
