package com.server.metrics.test.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.server.metrics.test.model.Test;

class TestStub {
	private static Map<Long, Test> tests = new HashMap<>();
	private static Long idIndex = 2L;

	private TestStub() {
	}

	static {
		Test a = new Test(1L, "RSTDEMO01U");
		tests.put(1L, a);
		Test b = new Test(2L, "RSTDEMO01T");
		tests.put(2L, b);
	}

	public static List<Test> list() {
		return new ArrayList<>(tests.values());
	}

	public static Test create(Test test) {
		idIndex += idIndex;
		test.setId(idIndex);
		tests.put(idIndex, test);
		return test;
	}

	public static Test get(Long id) {
		return tests.get(id);
	}

	public static Test update(Long id, Test test) {
		tests.put(id, test);
		return test;
	}

	public static Test delete(Long id) {
		return tests.remove(id);
	}
}
