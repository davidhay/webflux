package com.ealanta.reactive.entity;

import org.springframework.data.annotation.Id;
public record Customer(@Id Long id, String first, String last) {
}
