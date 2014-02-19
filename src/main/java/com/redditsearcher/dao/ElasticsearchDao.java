package com.redditsearcher.dao;

import java.util.List;

import com.redditsearcher.model.Link;

public interface ElasticsearchDao {

    List<Link> search(String query);
}
