package com.example.evinochallenge.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

@JsonIgnoreProperties(ignoreUnknown = true)
data class User(
    @JsonProperty("id")
    var id: Int,

    @JsonProperty("login")
    var login: String,

    @JsonProperty("pass")
    var pass: String

) : Serializable
