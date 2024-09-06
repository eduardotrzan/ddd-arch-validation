package com.ddd.arch.validation.example;

import com.ddd.arch.validation.annotation.AggregateRoot;

@AggregateRoot
public record AggregateRootObjectB(
        AggregateRootObjectA aggregateRootObjectA
) {
}
