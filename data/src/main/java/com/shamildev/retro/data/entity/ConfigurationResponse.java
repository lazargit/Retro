package com.shamildev.retro.data.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Schamil  on 03.11.2017.
 */

public class ConfigurationResponse implements Serializable,Entity {

        @SerializedName("images")
        @Expose
        private ConfigurationImages images;

        @SerializedName("change_keys")
        @Expose
        private List<String> changeKeys = null;
        private final static long serialVersionUID = -1482538082934399676L;



        public ConfigurationImages getImages() {
            return images;
        }

        public void setImages(ConfigurationImages images) {
            this.images = images;
        }

        public List<String> getChangeKeys() {
            return changeKeys;
        }

        public void setChangeKeys(List<String> changeKeys) {
            this.changeKeys = changeKeys;
        }


}
