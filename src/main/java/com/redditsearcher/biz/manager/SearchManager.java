package com.redditsearcher.biz.manager;

import com.redditsearcher.model.Link;

import java.util.Collection;

public interface SearchManager {
    Collection<Link> search(String query);
}
