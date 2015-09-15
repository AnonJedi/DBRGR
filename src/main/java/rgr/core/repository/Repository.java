package rgr.core.repository;

import org.springframework.beans.factory.annotation.Autowired;
import rgr.core.mappers.Mapper;

/**
 * Created by vik on 09.09.15.
 */

@org.springframework.stereotype.Repository
public class Repository implements IRepository {

    @Autowired
    private Mapper mapper;


}
