package com.ddd.arch.validation.check;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

import com.ddd.arch.validation.annotation.AggregateRoot;
import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaField;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;

import java.util.Set;

public class AggregateRootChecker {

    public static void checkAggregateRoots(String packageToScan) {
        var importedClasses = new ClassFileImporter().importPackages(packageToScan);

        ArchRule rule = classes()
                .that().areAnnotatedWith(AggregateRoot.class)
                .should(notHaveFieldsWithAggregateRootType());

        rule.check(importedClasses);
    }

    private static ArchCondition<JavaClass> notHaveFieldsWithAggregateRootType() {
        return new ArchCondition<>("not have fields of a type annotated with @AggregateRoot") {
            @Override
            public void check(JavaClass clazz, ConditionEvents events) {
                Set<JavaField> fields = clazz.getAllFields();
                for (JavaField field : fields) {
                    if (field.getRawType().isAnnotatedWith(AggregateRoot.class)) {
                        String message = String.format("Class %s has a field %s of type %s which is annotated with @AggregateRoot",
                                clazz.getFullName(), field.getName(), field.getRawType().getFullName());
                        events.add(SimpleConditionEvent.violated(field, message));
                    }
                }
            }
        };
    }
}
