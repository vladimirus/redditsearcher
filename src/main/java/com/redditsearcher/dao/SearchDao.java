package com.redditsearcher.dao;

import com.redditsearcher.model.Link;

import java.util.List;

public interface SearchDao {

    List<Link> search(String query);

    long countAll();
}
