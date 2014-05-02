package com.redditsearcher.biz.manager;

import com.redditsearcher.model.Link;

import java.util.List;

public interface SearchManager {
    List<Link> search(String query);
}
