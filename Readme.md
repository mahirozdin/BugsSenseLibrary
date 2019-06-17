Bugs Sense
=====
[![](https://jitpack.io/v/mahirozdin/MyApplication333.svg)](https://jitpack.io/#mahirozdin/MyApplication333)

Bugs Sense is a free crash tracking application. You can follow the instructions for free at BugsSense.Com. Or directly follow the installation instructions below.

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
        fh.sendFeedback("title","content",this);
```
