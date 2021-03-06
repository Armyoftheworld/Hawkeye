# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/army/Library/Android/sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

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

#基线包使用，生成mapping.txt
-printmapping mapping.txt
#生成的mapping.txt在app/buidl/outputs/mapping/release路径下，移动到/app路径下

#修复后的项目使用，保证混淆结果一致
#-applymapping mapping.txt

#hotfix
-keep class com.taobao.sophix.**{*;}
-keep class com.ta.utdid2.device.**{*;}

#萤石云
-dontwarn com.ezviz.player.**
-keep class com.ezviz.player.** { *;}
-dontwarn com.ezviz.statistics.**
-keep class com.ezviz.statistics.** { *;}
-dontwarn com.ezviz.stream.**
-keep class com.ezviz.stream.** { *;}
-dontwarn com.hik.**
-keep class com.hik.** { *;}
-dontwarn com.hikvision.**
-keep class com.hikvision.** { *;}
-dontwarn com.videogo.**
-keep class com.videogo.** { *;}
-dontwarn com.videogo.**
-keep class org.MediaPlayer.PlayM4.** { *;}
#Gson混淆配置
-keepattributes Annotation
-keep class sun.misc.Unsafe { *; }
-keep class com.idea.fifaalarmclock.entity.*
-keep class com.google.gson.stream.* { *; }