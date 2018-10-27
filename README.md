# ProgressButton

ProgressButton is simple library for show easily button with progress. for example you can use it as button download  

<img align="left" width="40%" src="https://www.oxima.ir/resources/images/articles/source_hub/progressbutton1.jpeg">
<img align="top" width="40%" src="https://www.oxima.ir/resources/images/articles/source_hub/progressbutton2.jpeg">

## Features
* MinSdk 14
* set font for button
* set color
* set progress
* set corner_redius
* ...

## Getting Started

### Gradle setup

In your gradle project :

```xml
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```
In your gradle module :

```xml
	        implementation 'com.github.hosein398:ProgressButton:0.1.0'
```

### Usage

you can use ProgressButton very easy, you should add below code in xml of activity or fragment ...:

```html
<ir.oxima.progressbutton.ProgressButton
            android:id="@+id/btn_progress"
            android:layout_width="100sp"
            android:layout_height="35sp"
            app:pb_color="@color/colorAccent"
            app:pb_stroke="1sp"
            app:pb_text_size="12sp"
            app:pb_corener_redius="3sp"
            app:pb_text="Install"/>
```

you can get and set State for button very easily:


```java
btn_progress.setState(ButtonState.Progress);
ButtonState state = btn_progress.getState();
```


## Authors

* **Hosein Raeisi** - *work at* - [Oxima](https://oxima.ir)
See also the list of [projects](https://github.com/hosein398?tab=repositories).

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
