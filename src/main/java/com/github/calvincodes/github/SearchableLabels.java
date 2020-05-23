package com.github.calvincodes.github;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchableLabels {

    public static List<String> SEARCHABLE_LABELS = new ArrayList<>(
            Arrays.asList(
                    "newbie",
                    "first-timers-only",
                    "\"good first issue\"", // Official GitHub issue.g
                    "good-first-issue"
            ));
}
