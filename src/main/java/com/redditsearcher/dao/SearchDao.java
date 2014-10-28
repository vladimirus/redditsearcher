package com.redditsearcher.dao;

import com.redditsearcher.model.Link;

import java.util.Collection;

public interface SearchDao {

    Collection<Link> search(String query);

    long countAll();
}
