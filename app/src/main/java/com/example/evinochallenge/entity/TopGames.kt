package com.example.evinochallenge.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

@JsonIgnoreProperties(ignoreUnknown = true)
data class TopGames(
    @JsonProperty("top")
    var result: List<Top?>,

    @JsonProperty("_total")
    var total: Double?
) : Serializable
