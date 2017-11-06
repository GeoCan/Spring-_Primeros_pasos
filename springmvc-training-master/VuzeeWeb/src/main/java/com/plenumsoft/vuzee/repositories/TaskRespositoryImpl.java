package com.plenumsoft.vuzee.repositories;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.SqlResultSetMapping;

import com.plenumsoft.vuzee.entities.TaskState;
import com.plenumsoft.vuzee.entities.TaskTotales;

public class TaskRespositoryImpl implements TaskRespositoryCustom {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<TaskTotales> GetTaskCount() {
		// Query query = entityManager.createNativeQuery("SELECT * FROM tasks",
		// Task.class);
		Query query = entityManager.createNativeQuery(
				"select c.id as candidate_id, c.name , t.task_type as task_state , count(t.task_type) as total from candidates c left join tasks t on t.candidate_id = c.id group by c.id,  t.task_type");

		List<Object[]> results = query.getResultList();

		List<TaskTotales> totales = new ArrayList<>();

		results.stream().forEach((record) -> {
			TaskTotales total = new TaskTotales();
			total.setCandidateId(((BigInteger) record[0]).longValue());
			total.setName((String) record[1]);
			if (record[2] != null)
				total.setTaskState(TaskState.from((int) record[2]));
			total.setTotal(((BigInteger) record[3]).longValue());
			// Long id = ((BigInteger) record[0]).longValue();
			// String firstName = (String) record[1];
			// String lastName = (String) record[2];
			// Integer version = (Integer) record[3];

			totales.add(total);
		});
		return totales;
	}

}
