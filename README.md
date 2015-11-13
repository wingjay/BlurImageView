[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-BlurImageView-green.svg?style=true)](https://android-arsenal.com/details/1/2768)
# BlurImageView
BlurImageView For Android, you can load your image progressively like [Medium](https://medium.com/@wing_jay/thinking-about-the-way-of-loading-picture-by-medium-4adfe792b437).

###How it works?
1. First it will show user a blurry image;
2. At the same time, it starts to load the real image;
3. once loaded, replace the blurry one automatically.

![](https://github.com/wingjay/BlurImageView/blob/master/ReadMe/blurImageView_nm.gif)

###Why I do this?
This lib is inspired by [Medium](https://medium.com/@wing_jay/thinking-about-the-way-of-loading-picture-by-medium-4adfe792b437) and users love this way of loading pics, Because a beautiful Blurry pic is always better than a blank area or an ugly thumbnail.

###How to use it?
1. You can fork and run this code directly.
2. `blurImageView.setBlurImageByUrl(blurImageUrl);` it will **load and blur** a image.
3. `fullBlurImageView.setFullImageByUrl(blurImageUrl, normalImageUrl);` it will **load two images progressively**.


###Related resource
Thanks for the algorithms: `Stack Blur`: http://www.quasimondo.com/StackBlurForCanvas/StackBlurDemo.html

###Reach me
You can get information about me and reach me in my github page: https://github.com/wingjay

Feel free to give me advices by <mailto:yinjiesh@126.com>

######Thanks!

