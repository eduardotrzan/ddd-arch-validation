package com.ddd.arch.validation.test;

import com.ddd.arch.validation.check.AggregateRootChecker;

import org.junit.jupiter.api.Test;

public class ArchUnitValidationTest {

    @Test
    public void validateAggregateRootArchitecture() {
        // Adjust the package name to where your AggregateRoots are defined
        AggregateRootChecker.checkAggregateRoots("*");
    }
}
