# Fluid Layouts

Layouts are used to organize widgets, the different types of layouts are `Horizontal`, `Vertical`, `Grid`
and `Scrolling`

## Create Horizontal List

Horizontal lists are used to organize widgets horizontally.

### Paremeters

| Name      | Type                  | Description                           |
|-----------|-----------------------|---------------------------------------|
| Label     | Component             | This is the title of the panel        |
| X         | Integer               | This is the X position                |
| Y         | Integer               | This is the Y position                |
| Width     | Integer               | This is the width of the panel        |
| Height    | Integer               | This is the height of the panel       |
| Space     | Integer               | This is the space between elements    |
| Alignment | Alignment             | This is the alignment of the elements |
| Children  | Abstract Widget Array | This is an array of all the children  |

### Alignment
There are 5 Alignment options and DEFAULT is what ever is default for the panel.  
1. `DEFAULT` - This is the **default** alignment for the panel
2. `CENTER` - This will align all the children to the **center**.
3. `LEFT` - This will align the children to the **left**. This does **_NOT_** work for vertical.
4. `RIGHT` - This will align the children to the **right**. This does **_NOT_** work for vertical.
5. `TOP` - This will align the children to the **top**. This does **_NOT_** work for horizontal.
6. `BOTTOM` - This will align the children to the **bottom**. This does **_NOT_** work for horizontal.

### Example:
Create the panel element.   
This is the same for each of the panel types.
```java
HorizontalListWidget horizontalList = new HorizontalListWidget(Component.empty(), 0, 0, 500, 500, 20, Alignment.CENTER, WIDGET_1, WIDGET_2 //. ETC);
```
Now we need to add it to the screen.  If your using [FluidScreen](/docs/screen.md) add the following to the `init` function

```java
//...
addRenderableWidget(horizontalList);
//...
```
