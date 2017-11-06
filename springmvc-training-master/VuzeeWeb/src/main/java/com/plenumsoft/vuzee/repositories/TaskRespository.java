package com.plenumsoft.vuzee.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.plenumsoft.vuzee.entities.Task;

@Repository
public interface TaskRespository extends CrudRepository<Task, Long>, TaskRespositoryCustom {

}

