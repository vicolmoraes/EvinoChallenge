package com.example.evinochallenge.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

@JsonIgnoreProperties(ignoreUnknown = true)
data class ChuckFact(
    @JsonProperty("icon_url")
    var icon_url: String,

    @JsonProperty("id")
    var id: String,

    @JsonProperty("url")
    var url: String,

    @JsonProperty("value")
    var value: String,

    @JsonProperty("categories")
    var categories: List<String>,

    @JsonProperty("created_at")
    var created_at: String
) : Serializable
