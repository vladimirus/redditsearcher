package com.redditsearcher.biz.manager;

import com.redditsearcher.dao.SearchDao;
import com.redditsearcher.model.Link;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * A class which connects to elastic search
 */
@Service
public class ElasticsearchManagerImpl implements SearchManager {
    @Autowired
    SearchDao searchDao;

    @Override
    public Collection<Link> search(String query) {
        Collection<Link> links = searchDao.search(query);
        return links;
    }
}
