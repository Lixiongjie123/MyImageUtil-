# MyImageUtil-
开发一个比较全版本适配的集成图片加载（还未实现） 和 照相以及相册选择的图片加载库

### 背景
 >前段时间做外包影响，为偷懒在网上寻找图片选择相关库，发现网上的的库绝大多数已经不能适配7.0以上的版本，再加上剪裁和照相请求的权限过程比较复杂，通过自己在项目中直接实现是比较不明智的，所以灵机一动决定，尝试用自己的能力看是否能，封装的出一个集图片选择和加载与一身的第三方库
 
## 依赖库添加：
1. 将其添加到存储库末尾的build.gradle中：
   
```gradle
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

2. 添加依赖项 

```gradle
	dependencies {
		implementation 'com.github.Lixiongjie123:MyImageUtil-:1.12'
	}
	}
```

##适用版本:

API:15~28

## 使用
### 照相和图库选择，我们需在new ImageLocalBuilder()后添加.album()/.camera()： 

```java

    pubic void choosePhoto ()  {
     myImageUtil = MyImageUtil.create(new ImageLocalBuilder()
                        .with(MainActivity.this)
                        .album()
                        .setBitmapListener(new ImageLocalListener() {
            @Override
            public void setBitmapOnclickListener(Bitmap bitmap) {

            }

            @Override
            public void setUriOnclickListener(Uri uri) {
              
            }
        }));
	
        myImageUtil.execute();
        }
        
         @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        myImageUtil.onActivityForResult(requestCode,resultCode,data);
    }

```

### 图片剪切 
#### 通过图库或者照相根源剪裁只用在new ImageLocalBuilder()后添加.crop(x,y),(注意在这之前必须添加.album()/.camera()表示剪裁来源)：

```java 

 MyImageUtil myImageUtil;
 
 pubic void choosePhoto ()  {
 myImageUtil = MyImageUtil.create(new ImageLocalBuilder()
                        .with(MainActivity.this)
                        .camera()
                        .crop(200,100)
                        .setBitmapListener(new ImageLocalListener() {
            @Override
            public void setBitmapOnclickListener(Bitmap bitmap) {

            }

            @Override
            public void setUriOnclickListener(Uri uri) {
               
            }
        }));
        myImageUtil.execute();
}
   
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        myImageUtil.onActivityForResult(requestCode,resultCode,data);
    }

```
#### 或者通过Uri选择图片进行剪裁（注意这里不需要通过来源即**不需要添加.album()/.camera()**，但必须在crop中指定Uri）：

```java
pubic void choosePhoto ()  {
myImageUtil = MyImageUtil.create(new ImageLocalBuilder().with(MainActivity.this).crop(uri.200,200)setBitmapListener(new ImageLocalListener() {
            @Override
            public void setBitmapOnclickListener(Bitmap bitmap) {

            }

            @Override
            public void setUriOnclickListener(Uri uri) {
               
            }
        }));
        myImageUtil.execute();}
      
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        myImageUtil.onActivityForResult(requestCode,resultCode,data);
    }
```

## 权限注意的问题

### 7.0版本 ：需在Andriodmanifest文件中的对应进程添加如下：  

**注意authorities一般情况填上（你的包名+.provider），如需自行设定需在new ImageLocalBuilder()后添加.providerAuthorities(你的Authorities)**

```gradle

 <provider
            android:authorities="你的包名.provider"
            android:name="android.support.v4.content.FileProvider"
            android:exported="false"
            android:grantUriPermissions="true"
            >
            <meta-data
                android:name="android.support.FILE_PROVIDER_PATHS"
                android:resource="@xml/paths"/>
</provider>
```

添加自己authorities的情况：

```java
myImageUtil = MyImageUtil.create(new myImageUtil = MyImageUtil.create(new ImageLocalBuilder()
                        .providerAuthorities(自己的Authorities)
                        .with(MainActivity.this)
                        .camera()
                        .crop(200,100)
                        .setBitmapListener(new ImageLocalListener() {
			
			...
			
```

### 同样的在Andriodmanifest中加上权限请求：
```xml
 <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/> 
```

### 获取动态权限  

- 6.0版本的动态权限请求这里不一一演示这里，Demo的MainActivity查看，主要目的是在java代码里动态请求申请权限，并判断权限获得情况，从而判断是不是需要重新请求或者执行 图片选择库




