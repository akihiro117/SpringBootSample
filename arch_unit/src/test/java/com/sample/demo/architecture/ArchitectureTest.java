package com.sample.demo.architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import com.tngtech.archunit.library.Architectures;
import org.junit.jupiter.api.Test;
import org.springframework.web.bind.annotation.*;
import java.lang.reflect.*;

public class ArchitectureTest {
    @Test
    public void architectureTest() {
        JavaClasses classes = new ClassFileImporter().importPackages("com.sample.demo");
        Architectures.layeredArchitecture()
                .consideringAllDependencies()
                .layer("Controller").definedBy("..controller..")
                .layer("Service").definedBy("..service..")
                .layer("Infrastructure").definedBy("..infrastructure..")
                .whereLayer("Controller").mayNotBeAccessedByAnyLayer()
                .whereLayer("Service").mayOnlyBeAccessedByLayers("Controller")
                .whereLayer("Infrastructure").mayOnlyBeAccessedByLayers("Service")
                .check(classes);
    }

    @Test
    public void prohibitRequestMappingAnnotation() {
        JavaClasses classes
                = new ClassFileImporter().importPackages("com.sample.demo.controller.");
        ArchRule archRule = ArchRuleDefinition.methods().that()
                .arePublic().should()
                .notBeAnnotatedWith(RequestMapping.class);
        archRule.check(classes);
    }

//    @Test
//    public void shouldHaveMappingAnnotation() {
//        JavaClasses classes
//                = new ClassFileImporter().importPackages("com.sample.demo.controller");
//        ArchRule archRule = ArchRuleDefinition.methods().that()
//                .arePublic().should()
//                .beAnnotatedWith(GetMapping.class)
//                .orShould()
//                .beAnnotatedWith(PostMapping.class)
//                .orShould()
//                .beAnnotatedWith(PutMapping.class)
//                .orShould()
//                .beAnnotatedWith(DeleteMapping.class);
//        archRule.check(classes);
//    }

    @Test
    public void domainShouldBeRecord() {
        JavaClasses classes
                = new ClassFileImporter().importPackages("com.sample.demo.domain");
        ArchRule archRule = ArchRuleDefinition.classes().should().beRecords();
        archRule.check(classes);
    }

    @Test
    public void fieldShodNotBePublic() {
        JavaClasses classes
                = new ClassFileImporter().importPackages("com.sample.demo");
        long publicFieldCount
                = classes.stream().filter(clazz -> clazz.getAllFields().stream()
                        .anyMatch(field -> field.reflect().accessFlags()
                                .contains(AccessFlag.PUBLIC)))
                .count();
        if (publicFieldCount > 0) {
            throw new AssertionError();
        }
    }
}
