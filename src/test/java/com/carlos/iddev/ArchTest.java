package com.carlos.iddev;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.carlos.iddev");

        noClasses()
            .that()
            .resideInAnyPackage("com.carlos.iddev.service..")
            .or()
            .resideInAnyPackage("com.carlos.iddev.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.carlos.iddev.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
