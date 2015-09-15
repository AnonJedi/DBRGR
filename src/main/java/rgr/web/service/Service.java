package rgr.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import rgr.core.repository.IRepository;

/**
 * Created by vik on 09.09.15.
 */

@org.springframework.stereotype.Service
public class Service {

    @Autowired
    @Qualifier(value = "repository")
    private IRepository repository;
}
