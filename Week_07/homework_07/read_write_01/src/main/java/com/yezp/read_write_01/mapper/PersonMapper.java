package com.yezp.read_write_01.mapper;

import com.yezp.read_write_01.domain.Person;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Description:
 * Created on 2020/12/3 0:44.
 *
 * @author yezp
 */
@Mapper
public interface PersonMapper {

    List<Person> getPersonList();

    int insertPerson(@Param("person") Person person);

}
