package com.yezp.read_write_01.service.impl;

import com.yezp.read_write_01.domain.Person;
import com.yezp.read_write_01.mapper.PersonMapper;
import com.yezp.read_write_01.service.PersonService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description:
 * Created on 2020/12/3 0:46.
 *
 * @author yezp
 */
@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonMapper personMapper;

    @Override
    public List<Person> getPersonList() {
        return personMapper.getPersonList();
    }

    @Override
    public int insertPerson(Person person) {
        return personMapper.insertPerson(person);
    }
}
