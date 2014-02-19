package com.redditsearcher.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.redditsearcher.model.Link;

@Repository
public class ElasticsearchDaoImpl implements ElasticsearchDao {

    @Override
    public List<Link> search(String query) {
        // TODO Auto-generated method stub
        return null;
    }
}
