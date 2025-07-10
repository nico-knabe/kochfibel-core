package com.umfana.domain;

import java.time.Instant;

public record ExecutedCommand<T extends Command>(T command, Instant executedAt) {
}