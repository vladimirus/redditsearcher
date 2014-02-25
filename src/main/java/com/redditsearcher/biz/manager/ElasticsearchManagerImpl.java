package com.redditsearcher.biz.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redditsearcher.dao.SearchDao;
import com.redditsearcher.model.Link;

/**
 * A class which connects to elastic search
 */
@Service
public class ElasticsearchManagerImpl implements SearchManager {
    @Autowired
    SearchDao searchDao;

    @Override
    public List<Link> search(String query) {
        List<Link> links = searchDao.search(query);
        return links;
    }
}
