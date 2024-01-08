package com.example.exercise_19.domain.userDetails

import com.example.exercise_19.domain.users.UsersResponse

data class UserDetailsResponse(
    val data: UsersResponse,
//    val support: Support
) {
//    enum class Support(
//        val url: String,
//        val text: String
//    )
}


//{
//    "data": {
//    "id": 9,
//    "email": "tobias.funke@reqres.in",
//    "first_name": "Tobias",
//    "last_name": "Funke",
//    "avatar": "https://reqres.in/img/faces/9-image.jpg"
//},
//    "support": {
//    "url": "https://reqres.in/#support-heading",
//    "text": "To keep ReqRes free, contributions towards server costs are appreciated!"
//}
//}