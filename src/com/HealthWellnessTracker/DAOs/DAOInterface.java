package com.HealthWellnessTracker.DAOs;

import java.util.List;

public interface DAOInterface<T> {

	boolean insert(T newObj);
	T find(long id);
	int update(T updatedObj);
	int delete(long id);
	List<T> getAll();
}
