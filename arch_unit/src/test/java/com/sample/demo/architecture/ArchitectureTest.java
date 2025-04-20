package com.sample.demo.architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import com.tngtech.archunit.library.Architectures;
import org.junit.jupiter.api.Test;
import org.springframework.web.bind.annotation.*;

public class ArchitectureTest {

    @Test
    public void layeredArchitectureTest() {
        JavaClasses classes = new ClassFileImporter().importPackages("com.sample.demo.layered");
        Architectures.layeredArchitecture()
                .consideringAllDependencies()
                .layer("presentation").definedBy("..controller..")
                .layer("application").definedBy("..service..")
                .layer("domain").definedBy("..controller..")
                .layer("infrastructure").definedBy("..infrastructure..")
                .whereLayer("presentation").mayNotBeAccessedByAnyLayer()
                .whereLayer("application").mayOnlyBeAccessedByLayers("presentation")
                .whereLayer("domain").mayOnlyBeAccessedByLayers("application")
                .whereLayer("infrastructure").mayOnlyBeAccessedByLayers("application", "domain")
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

    @Test
    public void domainShouldBeRecord() {
        JavaClasses classes
                = new ClassFileImporter().importPackages("com.sample.demo.layered.domain");
        ArchRule archRule = ArchRuleDefinition.classes().should().beRecords();
        archRule.check(classes);
    }

    @Test
    public void fieldShodBeFinalAndPrivate() {
        JavaClasses classes
                = new ClassFileImporter().importPackages("com.sample.demo.layered");
        ArchRule archRule = ArchRuleDefinition.fields().should().beFinal().andShould().bePrivate();
        archRule.check(classes);
    }
}
