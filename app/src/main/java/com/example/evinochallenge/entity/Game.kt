package com.example.evinochallenge.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

@JsonIgnoreProperties(ignoreUnknown = true)
data class Game(
    @JsonProperty("name")
    var name: String?,

    @JsonProperty("id")
    var id: Long?,

    @JsonProperty("giantbomb_id")
    var giantbomb_id: Long?,

    @JsonProperty("box")
    var box: Image?,

    @JsonProperty("logo")
    var logo: Image?,

    @JsonProperty("localized_name")
    var localized_name: String?,

    @JsonProperty("locale")
    var locale: String?
) : Serializable
