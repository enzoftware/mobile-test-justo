package com.enzoftware.randomuser.domain.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


/**
 * Created by Enzo Lizama Paredes on 10/20/20.
 * Contact: lizama.enzo@gmail.com
 */

@JsonClass(generateAdapter = true)
data class RandomUser(
    val gender: String,
    val email: String,
    val nat: String,
    val phone: String,
    val cell: String,
    @field:Json(name = "picture")
    val picture: Picture,
    @field:Json(name = "name")
    val name: Name,
    @field:Json(name = "id")
    val identifier: Identifier,
    @field:Json(name = "login")
    val loginInformation: LoginInformation,
    @field:Json(name = "location")
    val location: Location,
    @field:Json(name = "dob")
    val dateOfBirthday: DateOfBirthday
)

@JsonClass(generateAdapter = true)
data class Name(
    val title: String,
    val first: String,
    val last: String
)

@JsonClass(generateAdapter = true)
data class Picture(
    val large: String,
    val medium: String,
    val thumbnail: String
)

@JsonClass(generateAdapter = true)
data class Identifier(
    val name: String,
    val value: String
)

@JsonClass(generateAdapter = true)
data class LoginInformation(
    val uuid: String,
    val username: String,
    val password: String
)

@JsonClass(generateAdapter = true)
data class Location(
    @field:Json(name = "street")
    val street: Street,
    val city: String,
    val state: String,
    val country: String,
    val postcode: Int
)


@JsonClass(generateAdapter = true)
data class Street(
    val number: Int,
    val name: String
)

@JsonClass(generateAdapter = true)
data class DateOfBirthday(
    val date: String,
    val age: Int
)

