[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-BlurImageView-green.svg?style=true)](https://android-arsenal.com/details/1/2768)

[![Release JitPack](https://img.shields.io/github/release/wingjay/blurimageview.svg?label=JitPack)](https://jitpack.io/#wingjay/blurimageview/v1.0)

# BlurImageView
BlurImageView For Android, you can load your image progressively like [Medium](https://medium.com/@wing_jay/thinking-about-the-way-of-loading-picture-by-medium-4adfe792b437).

###How it works?
1. First it will show user a blurry image;
2. At the same time, it starts to load the real image;
3. once loaded, replace the blurry one automatically.

<img src="https://github.com/wingjay/BlurImageView/blob/master/ReadMe/blurImageView_nm.gif" width="300">

###Playable apk
You can [Download sample apk](https://github.com/wingjay/blurimageview/blob/master/sample.apk) and play on your phone. Try it!

###Why I do this?
This lib is inspired by [Medium](https://medium.com/@wing_jay/thinking-about-the-way-of-loading-picture-by-medium-4adfe792b437) and users love this way of loading pics, Because a beautiful Blurry pic is always better than a blank area or an ugly thumbnail.

###How to get it? 
Simply add the dependency
```
dependencies {
	compile 'com.wingjay:blurimageviewlib:1.1.0'
}
```

###How to use it?
```java
    BlurImageView blurImageView = (BlurImageView) findViewById(R.id.XXX);
    blurImageView.setBlurImageByUrl(blurImageUrl);
```
   This will **load and blur** a image.
```
  BlurImageView fullBlurImageView = (BlurImageView) findViewById(R.id.XXX)
  fullBlurImageView.setFullImageByUrl(blurImageUrl, normalImageUrl);
```
  This will **load two images progressively**.
```
  blurImageView.setBlurFactor(blurFactor);
```
  This can set the factor of blurry, default 8 - 10.

###Related resource
Thanks for the algorithms: `Stack Blur`: http://www.quasimondo.com/StackBlurForCanvas/StackBlurDemo.html

###Reach me - wingjay
![](http://tp3.sinaimg.cn/1625892654/180/5739331233/1)

Weibo: http://weibo.com/u/1625892654

You can get information about me and reach me in my github page: https://github.com/wingjay

Feel free to give me advices by <mailto:yinjiesh@126.com>

######Thanks!

