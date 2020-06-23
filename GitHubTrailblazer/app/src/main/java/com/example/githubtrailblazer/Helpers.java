package com.example.githubtrailblazer;

import android.content.Context;
import com.google.gson.Gson;
import java.io.*;
import java.lang.reflect.Type;

/**
 * Helpers class
 */
public class Helpers {

    /**
     * Get raw JSON, map to corresponding class instance(s)
     * @param context - the context
     * @param rawJsonId - the raw JSON resource id
     * @param t - the type
     * @return the resulting object
     */
    public static Object fromRawJSON(Context context, int rawJsonId, Type t) {
        InputStream is = context.getResources().openRawResource(rawJsonId);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new Gson().fromJson(writer.toString(), t);
    }
}