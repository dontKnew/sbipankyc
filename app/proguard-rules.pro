# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# Keep the annotations that are used by Volley
-keepattributes *Annotation*,EnclosingMethod,Signature

# Keep classes that are used in Gson serialization/deserialization
-keep class com.google.gson.** { *; }

# Keep the support library classes
-keep class androidx.appcompat.** { *; }
-keep class com.google.android.material.** { *; }
-keep class androidx.constraintlayout.** { *; }

# Keep the classes and members of Google Play Services
-keep class com.google.android.gms.** { *; }

# Keep the testing related classes
-dontwarn junit.framework.**
-keep class androidx.test.** { *; }
-keep class androidx.test.runner.** { *; }
-keep class androidx.test.ext.junit.runners.** { *; }
-keep class androidx.test.espresso.** { *; }

# Keep the Parcelable implementation of your classes
-keepclassmembers class * implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator *;
}

# Preserve all native method names and the names of their classes.
-keepclasseswithmembernames class * {
    native <methods>;
}

# Preserve the special static methods that are required in all enumeration classes.
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# If you're using any other third-party libraries, you may need to add additional rules for them here.
