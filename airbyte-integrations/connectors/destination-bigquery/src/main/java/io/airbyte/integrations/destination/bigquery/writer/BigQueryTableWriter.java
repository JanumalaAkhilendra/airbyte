/*
 * Copyright (c) 2023 Airbyte, Inc., all rights reserved.
 */

package io.airbyte.integrations.destination.bigquery.writer;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.cloud.bigquery.TableDataWriteChannel;
import com.google.common.base.Charsets;
import io.airbyte.commons.json.Jsons;
import io.airbyte.integrations.destination.s3.writer.DestinationWriter;
import io.airbyte.protocol.models.v0.AirbyteRecordMessage;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BigQueryTableWriter implements DestinationWriter {

  private static final Logger LOGGER = LoggerFactory.getLogger(BigQueryTableWriter.class);

  private final TableDataWriteChannel writeChannel;

  public BigQueryTableWriter(TableDataWriteChannel writeChannel) {
    this.writeChannel = writeChannel;
  }

  @Override
  public void initialize() throws IOException {}

  @Override
  public void write(UUID id, AirbyteRecordMessage recordMessage) {
    throw new RuntimeException("This write method is not used!");
  }

  @Override
  public void write(JsonNode formattedData) throws IOException {

    try {
      writeChannel.write(ByteBuffer.wrap((Jsons.serialize(formattedData) + "\n").getBytes(Charsets.UTF_8)));
    } catch (Exception e) {
      LOGGER.error("BigQueryTableWriter");
      LOGGER.error(formattedData.toPrettyString());
      throw e;
    }
  }

  @Override
  public void write(String formattedData) throws IOException {

    try {
      writeChannel.write(ByteBuffer.wrap((formattedData + "\n").getBytes(Charsets.UTF_8)));
    } catch (Exception e) {
      LOGGER.error("BigQueryTableWriter");
      LOGGER.error(formattedData);
      throw e;
    }
  }

  @Override
  public void close(boolean hasFailed) throws IOException {
    this.writeChannel.close();
  }

  public TableDataWriteChannel getWriteChannel() {
    return writeChannel;
  }

}
