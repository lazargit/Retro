/*
 * Copyright 2017 Vandolf Estrellado
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.shamildev.retro.data.net.auth;


import com.google.auto.value.AutoValue;

/**
 * Contains auth information.
 */
@AutoValue
//@JsonDeserialize(builder = AutoValue_AuthToken.Builder.class)
abstract class AuthToken {

    //@JsonProperty("access_token")
    abstract String accessToken();

    //@JsonProperty("token_type")
    abstract String tokenType();

    //@JsonProperty("expires_in")
    abstract long expiresInSeconds();

    /**
     * Builder used to create instances of {@link AuthToken}.
     */
    @AutoValue.Builder
   // @JsonIgnoreProperties(ignoreUnknown = true)
    abstract static class Builder {

        //@JsonProperty("access_token")
        abstract Builder accessToken(String accessToken);

        //@JsonProperty("token_type")
        abstract Builder tokenType(String tokenType);

        //@JsonProperty("expires_in")
        abstract Builder expiresInSeconds(long expiresInSeconds);

        abstract AuthToken build();
    }
}
