package com.onseo.sportsbookretrievingservice.config;

import com.hazelcast.nio.serialization.compact.CompactReader;
import com.hazelcast.nio.serialization.compact.CompactWriter;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class InstantCompactSerializerTest {

    private final InstantCompactSerializer serializer = new InstantCompactSerializer();

    @Test
    public void shouldReturnCorrectClassType() {
        assertEquals(Instant.class, serializer.getCompactClass());
    }

    @Test
    public void shouldReturnCorrectTypeName() {
        assertEquals("java.time.Instant", serializer.getTypeName());
    }

    @Test
    public void shouldReadInstantFromReader() {
        CompactReader reader = mock(CompactReader.class);
        long epochMilli = System.currentTimeMillis();
        when(reader.readInt64("epochMilli")).thenReturn(epochMilli);

        Instant result = serializer.read(reader);

        assertEquals(Instant.ofEpochMilli(epochMilli), result);
    }

    @Test
    public void shouldWriteInstantToWriter() {
        CompactWriter writer = mock(CompactWriter.class);

        long epochMilli = System.currentTimeMillis();
        Instant testInstant = Instant.ofEpochMilli(epochMilli);

        serializer.write(writer, testInstant);

        verify(writer, times(1)).writeInt64(eq("epochMilli"), eq(epochMilli));
    }
}