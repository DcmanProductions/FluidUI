# Fluid Theme

Fluid Theme class is where all the interface styling is stored.  
Initialize your theme in your mods client entry point.

## Add Import Statement

```java
import chase.minecraft.architectury.fluidui;
```

## Set the Themes Colors

```java
// Sets the color of the hover background for dropdown menus
FluidTheme.setDropdownHoverBackgroundColor(0xFF_FF_FF);

// Sets the color of the hover text for dropdown menus
FluidTheme.setDropdownHoverForegroundColor(0xFF_FF_FF);

// Sets the color of all screen backgrounds
FluidTheme.setBackgroundColor(0xFF_FF_FF);

// Sets the color of the primary color used for accenting
FluidTheme.setPrimaryColor(0xFF_FF_FF);

// Sets the color of all the widgets background
FluidTheme.setWidgetBackgroundColor(0xFF_FF_FF);

// Sets the color of all the widgets text 
FluidTheme.setWidgetForegroundColor(0xFF_FF_FF);

// Sets the color of all the widgets background when hovering
FluidTheme.setWidgetHoverBackgroundColor(0xFF_FF_FF);

// Sets the color of all the widgets text when hovering
FluidTheme.setWidgetHoverForegroundColor(0xFF_FF_FF);

// Sets the color of widgets borders
FluidTheme.setBorderColor(0xFF_FF_FF);
```