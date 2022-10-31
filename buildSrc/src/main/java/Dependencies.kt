object Params {
    /**
     * @param type variable of [gradle]
     */
    const val ApiKeyParam = "API_KEY"
    /**
     * @param type variable of [BaseUrl]
     */
    const val BaseUrl = "BASE_URL"
    /**
     * @param type variable of [OriginalImageUrl]
     */
    const val OriginalImageUrl = "ORIGINAL_IMAGE_URL"
    /**
     * @param type variable of [String]
     */
    const val String = "String"
}

object Versions {
    /**
     * @param type variable of [gradle]
     */
    const val gradle = "7.0.3"
    /**
     * @param type variable of [plugin]
     */
    const val plugin = "1.6.10"
    /**
     * @param type variable of [daggerHilt]
     */
    const val daggerHilt = "2.38.1"
    /**
     * @param type variable of [daggerHilt]
     */
    const val hiltLifecycle = "1.0.0-alpha03"
    /**
     * @param type variable of [hiltAndroidCompiler]
     */
    const val hiltAndroidCompiler = "2.37"
    /**
     * @param type variable of [hiltCompiler]
     */
    const val hiltCompiler = "1.0.0"
    /**
     * @param type variable of [core]
     */
    const val core = "1.8.0"
    /**
     * @param type variable of [appCompat]
     */
    const val appCompat = "1.5.0"
    /**
     * @param type variable of [material]
     */
    const val material = "1.6.1"
    /**
     * @param type variable of [constraintLayout]
     */
    const val constraintLayout = "2.1.4"
    /**
     * @param type variable of [extJunit]
     */
    const val extJunit = "1.1.3"
    /**
     * @param type variable of [extJunit]
     */
    const val espresso = "3.4.0"
    /**
     * @param type variable of [complerSdk]
     */
    const val complerSdk = 32
    /**
     * @param type variable of [applicationId]
     */
    const val applicationId = "com.example.movieappassessment"
    /**
     * @param type variable of [minSdk]
     */
    const val minSdk = 26
    /**
     * @param type variable of [targetSdk]
     */
    const val targetSdk = 32
    /**
     * @param type variable of [versionCode]
     */
    const val versionCode = 1
    /**
     * @param type variable of [versionName]
     */
    const val versionName = "1.0"
    /**
     * @param type variable of [recyclerView]
     */
    const val recyclerView = "1.2.1"
    /**
     * @param type variable of [lifecycle]
     */
    const val lifecycle = "2.5.1"
    /**
     * @param type variable of [navigationFragment]
     */
    const val navigationFragment = "2.5.2"
    /**
     * @param type variable of [navigationUi]
     */
    const val navigationUi = "2.5.2"
    /**
     * @param type variable of [lottie]
     */
    const val lottie = "5.2.0"
    /**
     * @param type variable of [legacy]
     */
    const val legacy = "1.0.0"
    /**
     * @param type variable of [splash]
     */
    const val splash = "1.0.0-beta01"
    /**
     * @param type variable of [room]
     */
    const val room = "2.4.3"
    /**
     * @param type variable of [glide]
     */
    const val glide = "4.12.0"
    /**
     * @param type variable of [nav]
     */
    const val nav = "2.5.3"
    /**
     * @param type variable of [dotViewPager]
     */
    const val dotViewPager = "4.3"
    /**
     * @param type variable of [retrofit]
     */
    const val retrofit = "2.9.0"
    /**
     * @param type variable of [okhttp]
     */
    const val okhttp = "5.0.0-alpha.2"
    /**
     * @param type variable of [coroutineCore]
     */
    const val coroutineCore = "1.4.1"
    /**
     * @param type variable of [coroutineAndroid]
     */
    const val coroutineAndroid = "1.6.1"
    /**
     * @param type variable of [gson]
     */
    const val gson = "2.9.0"
    /**
     * @param type variable of [activity]
     */
    const val activity = "1.5.1"
    /**
     * @param type variable of [fragment]
     */
    const val fragment = "1.5.4"
    /**
     * @param type variable of [dataStore]
     */
    const val dataStore = "1.0.0"
}

object KotlinLibraries {
    /**
     * @param type dependecies of type [gradleDependencies]
     */
    const val gradleDependencies = "com.android.tools.build:gradle:${Versions.gradle}"
    /**
     * @param type dependecies of type [pluginDependencies]
     */
    const val pluginDependencies = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.plugin}"
    /**
     * @param type dependecies of type [daggerHilt]
     */
    const val daggerHilt = "com.google.dagger:hilt-android-gradle-plugin:${Versions.daggerHilt}"
    /**
     * @param type dependecies of type [coroutineCore]
     */
    const val coroutineCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutineCore}"
    /**
     * @param type dependecies of type [coroutineAndroid]
     */
    const val coroutineAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutineAndroid}"

}

object AndroidLibraries {
    /**
     * @param type dependecies of type [core]
     */
    const val core = "androidx.core:core-ktx:${Versions.core}"
    /**
     * @param type dependecies of type [appCompat]
     */
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    /**
     * @param type dependecies of type [material]
     */
    const val material = "com.google.android.material:material:${Versions.material}"
    /**
     * @param type dependecies of type [constraintLayout]
     */
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    /**
     * @param type dependecies of type [recyclerView]
     */
    const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"
    /**
     * @param type dependecies of type [lifecycleLiveData]
     */
    const val lifecycleLiveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
    /**
     * @param type dependecies of type [lifecycleViewModel]
     */
    const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    /**
     * @param type dependecies of type [lifecycleRuneTime]
     */
    const val lifecycleRuneTime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
    /**
     * @param type dependecies of type [navigationFragment]
     */
    const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigationFragment}"
    /**
     * @param type dependecies of type [navigationUi]
     */
    const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.navigationUi}"
    /**
     * @param type dependecies of type [legacy]
     */
    const val legacy = "androidx.legacy:legacy-support-v4:${Versions.legacy}"
    /**
     * @param type dependecies of type [splash]
     */
    const val splash = "androidx.core:core-splashscreen:${Versions.splash}"
    /**
     * @param type dependecies of type [activity]
     */
    const val activity = "androidx.activity:activity-ktx:${Versions.activity}"
    /**
     * @param type dependecies of type [fragment]
     */
    const val fragment = "androidx.fragment:fragment-ktx:${Versions.fragment}"
    /**
     * @param type dependecies of type [dataStore]
     */
    const val dataStore = "androidx.datastore:datastore-preferences:${Versions.dataStore}"
    /**
     * @param type dependecies of type [hiltAndroid]
     */
    const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.daggerHilt}"
    /**
     * @param type dependecies of type [hiltLifecycle]
     */
    const val hiltLifecycle = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.hiltLifecycle}"
    /**
     * @param type dependecies of type [roomRuntime]
     */
    const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
    /**
     * @param type dependecies of type [roomKtx]
     */
    const val roomKtx = "androidx.room:room-ktx:${Versions.room}"
    /**
     * @param type dependecies of type [navFragment]
     */
    const val navFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.nav}"
    /**
     * @param type dependecies of type [navUi]
     */
    const val navUi = "androidx.navigation:navigation-ui-ktx:${Versions.nav}"
}

object Libraries {
    /**
     * @param type dependecies of type [lottie]
     */
    const val lottie = "com.airbnb.android:lottie:${Versions.lottie}"
    /**
     * @param type dependecies of type [dotViewPager]
     */
    const val dotViewPager = "com.tbuonomo:dotsindicator:${Versions.dotViewPager}"
    /**
     * @param type dependecies of type [glide]
     */
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    /**
     * @param type dependecies of type [retrofit]
     */
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    /**
     * @param type dependecies of type [retrofitGson]
     */
    const val retrofitGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    /**
     * @param type dependecies of type [gson]
     */
    const val gson = "com.google.code.gson:gson:${Versions.gson}"
    /**
     * @param type dependecies of type [okhttp]
     */
    const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    /**
     * @param type dependecies of type [logging]
     */
    const val logging = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
}

object Kapt {
    /**
     * @param type dependecies of type [roomCompiler]
     */
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    /**
     * @param type dependecies of type [glideCompiler]
     */
    const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide}"
    /**
     * @param type dependecies of type [glideCompiler]
     */
    const val lifecycleCompiler = "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycle}"
    /**
     * @param type dependecies of type [hiltAndroidCompiler]
     */
    const val hiltAndroidCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hiltAndroidCompiler}"
    /**
     * @param type dependecies of type [hiltCompiler]
     */
    const val hiltCompiler = "androidx.hilt:hilt-compiler:${Versions.hiltCompiler}"
}

object AnnotationProcessor {
    /**
     * @param type dependecies of type [roomCompiler]
     */
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"

}

object TestLibraries {
    /**
     * @param type dependecies of type [junit]
     */
    const val junit = "junit:junit:4.+"
    /**
     * @param type dependecies of type [extJunit]
     */
    const val extJunit = "androidx.test.ext:junit:${Versions.extJunit}"
    /**
     * @param type dependecies of type [espresso]
     */
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
}