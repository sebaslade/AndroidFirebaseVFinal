package com.sebastianlaos.android_firebase.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.logEvent
import com.google.firebase.auth.FirebaseUser
import com.sebastianlaos.android_firebase.screens.Screen
import com.sebastianlaos.android_firebase.screens.Home
import com.sebastianlaos.android_firebase.screens.auth.ForgotPasswordScreen
import com.sebastianlaos.android_firebase.screens.auth.Login
import com.sebastianlaos.android_firebase.screens.auth.SignUpScreen
import com.sebastianlaos.android_firebase.utils.AnalyticsManager
import com.sebastianlaos.android_firebase.utils.AuthManager

@Composable
fun Navigation (context: Context, navController: NavHostController = rememberNavController()){
    var analytics: AnalyticsManager = AnalyticsManager(context)
    val authManager: AuthManager = AuthManager(context)

    val user: FirebaseUser? = authManager.getCurrentUser()

    Screen {
        NavHost(
            navController = navController,
            startDestination = if(user == null) Routes.Login.route else Routes.Login.route
        ){
            composable(Routes.Login.route){
                Login(
                    analytics = analytics,
                    auth = authManager,
                    navigation = navController
                )
            }
            composable(Routes.Home.route){
                Home(
                    analytics = analytics,
                    auth = authManager,
                    navigation = navController
                )
            }
            composable(Routes.SingUp.route){
                SignUpScreen(
                    analytics = analytics,
                    auth = authManager,
                    navigation = navController
                )
            }
            composable(Routes.ForgotPassword.route){
                ForgotPasswordScreen(
                    analytics = analytics,
                    auth = authManager,
                    navigation = navController
                )
            }
        }
    }
}

@Composable
fun trackScreen(name: String, analytics: FirebaseAnalytics){
    DisposableEffect(Unit){
        onDispose {
            analytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW){
                param(FirebaseAnalytics.Param.SCREEN_NAME, name)
            }
        }
    }
}
