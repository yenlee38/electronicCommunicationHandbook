package com.example.electroniccommunicationhandbook.util;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.Date;

public final class UnixEpochDateTypeAdapter   extends TypeAdapter<Date> {
    private static final TypeAdapter<Date> unixEpochDateTypeAdapter = new UnixEpochDateTypeAdapter();

    private UnixEpochDateTypeAdapter() {
    }

    public static TypeAdapter<Date> getUnixEpochDateTypeAdapter() {
        return unixEpochDateTypeAdapter;
    }

    @Override
    public Date read(final JsonReader in)
            throws IOException {
        if(in==null || in.equals("null"))
            return null;
        // this is where the conversion is performed
        return  new Date(in.nextLong());
    }

    @Override
    @SuppressWarnings("resource")
    public void write(final JsonWriter out, final Date value)
            throws IOException {
        if(value!= null || value.equals("null")){
            // write back if necessary or throw UnsupportedOperationException
            out.value(value.getTime());
        }

    }

}
