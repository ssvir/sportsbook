package com.onseo.sportsbookretrievingservice.config;

import com.hazelcast.nio.serialization.compact.CompactReader;
import com.hazelcast.nio.serialization.compact.CompactSerializer;
import com.hazelcast.nio.serialization.compact.CompactWriter;

import java.time.Instant;

public class InstantCompactSerializer implements CompactSerializer<Instant> {

    @Override
    public String getTypeName() {
        return "java.time.Instant";
    }

    @Override
    public Class<Instant> getCompactClass() {
        return Instant.class;
    }

    @Override
    public Instant read(CompactReader reader) {
        return Instant.ofEpochMilli(reader.readInt64("epochMilli"));
    }

    @Override
    public void write(CompactWriter writer, Instant instant) {
        writer.writeInt64("epochMilli", instant.toEpochMilli());
    }
}
