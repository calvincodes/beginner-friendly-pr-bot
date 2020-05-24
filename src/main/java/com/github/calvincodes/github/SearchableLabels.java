package com.github.calvincodes.github;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchableLabels {

    public static List<String> SEARCHABLE_LABELS = new ArrayList<>(
            Arrays.asList("beginner",
            "first-timers-only",
            "good-first-issue",
            "\"good first issue\"", // Official GitHub issue.
            "newbie"
            ));
}
