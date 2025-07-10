package com.umfana.queries.services;

import com.umfana.domain.models.tag.Tag;
import com.umfana.queries.QueryService;

import java.util.List;

public class GetTagsOverviewQueryService implements QueryService {

    public record TagCounter(Tag tag, int counter) {
    }

    public record TagOverview(List<TagCounter> tags) {
    }
}
