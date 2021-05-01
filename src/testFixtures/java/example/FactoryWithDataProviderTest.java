/*
 * Copyright 2021 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v2.0 which
 * accompanies this distribution and is available at
 *
 * https://www.eclipse.org/legal/epl-v20.html
 */

package example;

import static org.testng.Assert.fail;

import org.testng.annotations.Factory;
import org.testng.annotations.Test;

public class FactoryWithDataProviderTest {

	private final String param;

	@Factory(dataProvider = "strings", dataProviderClass = DataProviders.class)
	public FactoryWithDataProviderTest(String param) {
		this.param = param;
	}

	@Test
	public void a() {
		fail(param);
	}

	@Test
	public void b() {
		fail(param);
	}
}
