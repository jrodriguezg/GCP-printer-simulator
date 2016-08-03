package com.jmrodrigg.model.CDD;

import com.google.gson.annotations.SerializedName;

/**
 * Author: jrodriguezg
 * Date: 7/29/16
 */
public class Copies {
    @SerializedName("default")    public final Integer default_copies        = null;
    @SerializedName("max")        public final Integer max_copies            = null;

    @Override
    public String toString() {
        return "Copies{" +
                "default_copies=" + default_copies +
                ", max_copies=" + max_copies +
                '}';
    }
}
