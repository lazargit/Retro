package com.shamildev.retro.data.entity.mapper;

import com.google.gson.JsonObject;
import com.shamildev.retro.domain.models.Configuration;

/**
 * Created by Shamil Lazar on 15.08.2018.
 */

interface JsonMapper {

    Configuration map(JsonObject jsonobj);

}
