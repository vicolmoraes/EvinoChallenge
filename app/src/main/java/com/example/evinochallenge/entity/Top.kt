package com.example.evinochallenge.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

@JsonIgnoreProperties(ignoreUnknown = true)
data class Top(
    @JsonProperty("channels")
    var channels: Long?,

    @JsonProperty("viewers")
    var viewers: Long?,

    @JsonProperty("game")
    var game: Game?

) : Serializable
