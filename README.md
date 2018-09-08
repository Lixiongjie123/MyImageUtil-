# MyImageUtil-
开发一个集成图片加载 和 照相以及相册选择的图片加载库

## 依赖库添加：
1. 将其添加到存储库末尾的build.gradle中：
   
```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

2. 添加依赖项 

```
	dependencies {
		implementation 'com.github.User:Repo:Tag'
	}
```

## 使用
### 照相和图库选择： 

```

    pubic void choosePhoto ()  {
       myImageUtil = MyImageUtil.create(new ImageLocalBuilder().album().setBitmapListener(new ImageLocalListener() {
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
#### 通过图库或者照相根源剪裁：

```

 pubic void choosePhoto ()  {
 myImageUtil = MyImageUtil.create(new ImageLocalBuilder().with(MainActivity.this).album().crop(200,200)setBitmapListener(new ImageLocalListener() {
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
#### 通过Uri选择图片进行剪裁：

```
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

- 7.0版本添加 ：需在Andriodmanifest文件中的对应进程添加如下：  

**注意authorities一般情况填上你的包名+".provider"，需自行设定需设定+.providerAuthorities("你的Authorities")**

```
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
意外情况：
```
myImageUtil = MyImageUtil.create(new ImageLocalBuilder().providerAuthorities("com.example.lixiongjie.myimageutil.provider").with(MainActivity.this).album().setBitmapListener(new ImageLocalListener() 
```



- 同样的在Andriodmanifest中加上：
```
 <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/> 
```
获取权限  

- 6.0版本的动态权限请求这里不一一演示这里，Demo的MainActivity查看



