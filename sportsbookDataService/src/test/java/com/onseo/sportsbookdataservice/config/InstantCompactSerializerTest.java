package com.onseo.sportsbookdataservice.config;

import com.hazelcast.nio.serialization.compact.CompactReader;
import com.hazelcast.nio.serialization.compact.CompactWriter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.Instant;

public class InstantCompactSerializerTest {

    @Test
    void testRead() {
        CompactReader compactReader = Mockito.mock(CompactReader.class);
        Instant expectedInstant = Instant.ofEpochMilli(1628000000L);
        Mockito.when(compactReader.readInt64("epochMilli")).thenReturn(1628000000L);

        InstantCompactSerializer serializer = new InstantCompactSerializer();
        Instant resultInstant = serializer.read(compactReader);

        Assertions.assertEquals(expectedInstant, resultInstant);
    }

    @Test
    void testWrite() {
        CompactWriter compactWriter = Mockito.mock(CompactWriter.class);
        Instant instant = Instant.ofEpochMilli(1628000000L);

        InstantCompactSerializer serializer = new InstantCompactSerializer();
        serializer.write(compactWriter, instant);

        Mockito.verify(compactWriter).writeInt64("epochMilli", 1628000000L);
    }

    @Test
    void testGetCompactClass() {
        InstantCompactSerializer serializer = new InstantCompactSerializer();

        Class<Instant> compactClass = serializer.getCompactClass();

        Assertions.assertEquals(Instant.class, compactClass);
    }

    @Test
    void testGetTypeName() {
        InstantCompactSerializer serializer = new InstantCompactSerializer();

        String typeName = serializer.getTypeName();

        Assertions.assertEquals("java.time.Instant", typeName);
    }
}
