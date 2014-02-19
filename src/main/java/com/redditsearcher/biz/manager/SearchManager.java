package com.redditsearcher.biz.manager;

import java.util.List;

import com.redditsearcher.model.Link;

public interface SearchManager {

    List<Link> search(String query);

}
