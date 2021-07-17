/*
 * Copyright 2021 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v2.0 which
 * accompanies this distribution and is available at
 *
 * https://www.eclipse.org/legal/epl-v20.html
 */

package org.junit.support.testng.engine;

import static org.junit.platform.engine.discovery.DiscoverySelectors.selectClass;
import static org.junit.platform.testkit.engine.EventConditions.event;
import static org.junit.platform.testkit.engine.EventConditions.finishedSuccessfully;
import static org.junit.platform.testkit.engine.EventConditions.started;
import static org.junit.platform.testkit.engine.EventConditions.test;

import example.configparams.ReturnValuesTestCase;
import example.configparams.SystemPropertyProvidingListener;
import example.configparams.SystemPropertyReadingTestCase;

import org.junit.jupiter.api.Test;

class ConfigurationParametersIntegrationTests extends AbstractIntegrationTests {

	@Test
	void registersCustomListeners() {
		var testClass = SystemPropertyReadingTestCase.class;

		var results = testNGEngine() //
				.selectors(selectClass(testClass)) //
				.configurationParameter("testng.listeners", SystemPropertyProvidingListener.class.getName()) //
				.execute();

		results.allEvents().assertEventsMatchLooselyInOrder( //
			event(testClass(testClass), started()), //
			event(test("method:test()"), started()), //
			event(test("method:test()"), finishedSuccessfully()), //
			event(testClass(testClass), finishedSuccessfully()));
	}

	@Test
	void executesTestMethodsWithReturnValuesWhenEnabledViaConfigurationParameter() {
		var testClass = ReturnValuesTestCase.class;

		var results = testNGEngine() //
				.selectors(selectClass(testClass)) //
				.configurationParameter("testng.allowReturnValues", "true") //
				.execute();

		results.allEvents().assertEventsMatchLooselyInOrder( //
			event(testClass(testClass), started()), //
			event(test("method:test()"), started()), //
			event(test("method:test()"), finishedSuccessfully()), //
			event(testClass(testClass), finishedSuccessfully()));
	}

}