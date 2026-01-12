package com.lovable.com.lovable.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Plan {

    Long id;

    String name;

    String stripePriceId;
    Integer newProjects;
    Integer maxTokensPerDay;
    Integer maxPreviews; // max number of times have access of preview of what it created
    Boolean unlimitedAi; // unlimited access to LLM, ignore maxTokensPerDay if true

    Boolean active;
}
