Bugs Sense
=====
[![](https://jitpack.io/v/mahirozdin/BugsSenseLibrary.svg)](https://jitpack.io/#mahirozdin/BugsSenseLibrary)

Bugs Sense is a free crash tracking library. You can follow the instructions for free at BugsSense.Com. Or directly follow the installation instructions below.

Warning.
--------
System and application stability is currently in beta. We recommend that it is not currently used in applications that appeal to real users. We are not responsible for any problems that may occur. Feel free to ask any questions you may have at info@mizanbilisim.com.

How to install.
--------
1 - Add to project gradle.
```gradle
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  
```
2 - Add to module gradle.
```gradle
 dependencies {
	        implementation 'com.github.mahirozdin:BugsSenseLibrary:0.0.3'
	}
```
3 - Add to AndroidManifest.xml
```xml
 <uses-permission android:name="android.permission.INTERNET" />
```
 

Crash Handling
--------
```java
    Thread.UncaughtExceptionHandler defaultUEH = Thread.getDefaultUncaughtExceptionHandler();
        if (!(defaultUEH instanceof ExceptionHandler)) {
            Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(defaultUEH,MainActivity.this));
        }
```
Feedback Handling
--------
```java
        FeedbackHandler fh = new FeedbackHandler();
        fh.sendFeedback("title","content","email@email.com",this);
```

How to Track?
--------

1. Go to https://BugsSense.Com/manager/
2. Register a new account
3. Create new app and fill package name correctly
4. Tracking started!
