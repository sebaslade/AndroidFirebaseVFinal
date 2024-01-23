package com.sebastianlaos.android_firebase.navigation

sealed class Routes (val route:String){
    object Login: Routes("Login")
    object Home: Routes("Home")
    object  SingUp: Routes("SingUpScreen")
    object ForgotPassword: Routes("ForgotPassword Screen")
}