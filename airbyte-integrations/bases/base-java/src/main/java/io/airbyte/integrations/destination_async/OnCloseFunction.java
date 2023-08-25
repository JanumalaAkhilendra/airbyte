/*
 * Copyright (c) 2023 Airbyte, Inc., all rights reserved.
 */

package io.airbyte.integrations.destination_async;

import java.util.function.Consumer;

/**
 * Async version of
 * {@link io.airbyte.integrations.destination.buffered_stream_consumer.OnCloseFunction}. Separately
 * out for easier versioning.
 */
public interface OnCloseFunction extends Consumer<Boolean> {}
