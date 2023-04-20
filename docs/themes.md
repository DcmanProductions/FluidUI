<link href="/FluidUI/style.min.css" rel="stylesheet">
<link rel="shortcut icon" href="/FluidUI/images/Fluid UI Logo.svg" type="image/x-icon">

# Themes

Fluid Theme class is where all the interface styling is stored.  
Initialize your theme in your mods client entry point.

## Namespace

```java
import chase.minecraft.architectury.fluidui;
```

## Create JSON
First we need to create a theme.   
Create a json file in `/resources/assets/{modid}/fluid/themes/{theme name}.json`
```json5
{
  // The color of the background 
  "backgroundColor": "#111111",
  // The accent color 
  "primaryColor": "#e2564c",
  // The background color of the widgets 
  "widgetBackgroundColor": "#1a1a1a",
  // The foreground color of the widgets 
  "widgetForegroundColor": "#ffffff",
  // The hover color of the background 
  "widgetHoverBackgroundColor": "#2b2b2b",
  // The hover color of the foreground
  "widgetHoverForegroundColor": "#FFFFFF",
  // The dropdown item hover background color
  "dropdownHoverBackgroundColor": "#1a1a1a",
  // The dropdown item hover foreground color
  "dropdownHoverForegroundColor": "#FFFFFF",
  // The color of any borders
  "borderColor": "#24F65F"
}
```
## Registration
Next we need to register the theme, goto your client initialization and run:
```java
public static final FluidTheme THEME = FluidTheme.register("modid", "theme name"); // Theme name minus the .json
```

## PreInstalled
There are a few preinstalled themes you can use (if you're lazy).
1. DEFAULT
2. DARK
```java
public static final FluidTheme THEME = FluidTheme.DEFAULT;
```