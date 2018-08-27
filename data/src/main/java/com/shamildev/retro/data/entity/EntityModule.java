
package com.shamildev.retro.data.entity;


import com.shamildev.retro.data.entity.mapper.EntityMapperModule;

import dagger.Module;

/**
 * Provides entity dependencies.
 */
@Module(includes = {
        EntityMapperModule.class
        //,EntityValidatorModule.class
})
public abstract class EntityModule {



}
