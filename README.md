# imageadapter
[![](https://jitpack.io/v/jagamypriera/imageadapter.svg)](https://jitpack.io/#jagamypriera/imageadapter)
image adapter for view pager.
usage:
```java
ViewPager pager=(ViewPager)findViewById(R.id.yourviewpager);
ArrayList<String>images=new ArrayList<>();
images.add("image url")
pager.setAdapter(new ImageAdapter(getContext()).setImages(images).setZoomable(false);
```
