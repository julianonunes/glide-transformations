# glide-transformations

## ScreenGlareTransformation
Simulates the screen glare effect.

**Screenshot**

<img src="https://github.com/julianonunes/glide-transformations/blob/master/device-2017-03-03-124217.png?raw=true" width="400" />

**Code**
```
Glide.with(this)
    .load(R.drawable.lamborghini)
    .centerCrop()
    .transform(new ScreenGlareTransformation(getApplicationContext()))
    .into(imageView);
```
<meta property='og:image' content='https://github.com/julianonunes/glide-transformations/blob/master/device-2017-03-03-124217.png?raw=true'/>
