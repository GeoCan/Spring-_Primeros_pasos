package com.plenumsoft.vuzee.entities;

import java.util.HashMap;
import java.util.Map;

public enum TaskState {
	PENDING(0), INPROGRESS(1), DONE(2);

	private final int Code;

	private TaskState(int code) {
		Code = code;
	}

	public int getCode() {
		return Code;
	}

	 private static final Map<Integer, TaskState> _map = new HashMap<Integer, TaskState>();
	    static
	    {
	        for (TaskState task : TaskState.values())
	            _map.put(task.Code, task);
	    }
	 
	    /**
	     * Get difficulty from value
	     * @param value Value
	     * @return Difficulty
	     */
	    public static TaskState from(int value)
	    {
	        return _map.get(value);
	    }
}
