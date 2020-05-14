package com.example.evinochallenge.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

@JsonIgnoreProperties(ignoreUnknown = true)
data class ChuckFacts(
    @JsonProperty("result")
    var result: List<ChuckFact>,
    @JsonProperty("total")
    var total: Double
) : Serializable
