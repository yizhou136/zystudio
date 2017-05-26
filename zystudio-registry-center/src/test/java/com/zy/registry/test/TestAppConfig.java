package com.zy.registry.test;

import com.zy.registry.test.spring.AnnotationImportTest;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * @author by zy.
 */
@SpringBootApplication
@Import(AnnotationImportTest.class)
public class TestAppConfig {}