package com.moredev.spring.demospringdockerlog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

	// private final static Logger log =
	// Logger.getLogger(GreetingController.class.getName());

	static final Logger logger = LoggerFactory.getLogger(GreetingController.class);

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@RequestMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {

		logger.debug("-- debug message slf4j");
		logger.info("-- info message slf4j");
		logger.warn("-- warn message slf4j");
		logger.error("-- error message slf4j");
		if ("forceerror".equals(name)) {
			try {
				int i = 1 / 0;
				System.out.println(i);
			} catch (Exception exc) {
				logger.error("error message with stack trace slf4j", new Exception("I forced this exception", exc));
			}
		}
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}

}
