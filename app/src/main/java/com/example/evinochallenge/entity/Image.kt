package com.example.evinochallenge.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

@JsonIgnoreProperties(ignoreUnknown = true)
data class Image(
    @JsonProperty("large")
    var large: String,
    @JsonProperty("medium")
    var medium: String,
    @JsonProperty("small")
    var small: String,
    @JsonProperty("template")
    var template: String

) : Serializable
