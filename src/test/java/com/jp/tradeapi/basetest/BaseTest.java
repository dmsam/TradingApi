package com.jp.tradeapi.basetest;

import org.junit.Before;
import org.mockito.MockitoAnnotations;

public abstract class BaseTest {
	@Before
	public void prepare() {
		MockitoAnnotations.initMocks(this);
	}
}