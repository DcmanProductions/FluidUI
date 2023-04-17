<link href="/FluidUI/style.min.css" rel="stylesheet">
<link rel="shortcut icon" href="/FluidUI/images/Fluid UI Logo.svg" type="image/x-icon">

# Fluid Screen
Fluid screen is the base class for all of FluidUI's interfaces
## Create Test Screen
First we need to create a class that extends `FluidScreen`   
```java
import chase.minecraft.architectury.fluidui.gui.screen.FluidScreen;

public class TestScreen extends FluidScreen
{
	//....
```

## Constructors
With FluidScreens there are two constructors,  
the first one takes in a `Component` for the screens title and a `Screen` for the parent screen.  
The second one only takes in the `Component` for the title and the parent is **_NULL_**
```java
/**
 * Creates a fluid screen
 *
 * @param title  - Screens title
 * @param parent - the parent screen
 */
public TestScreen(Component title, @Nullable Screen parent)
{
    super(title, parent);
}

/**
 * Creates a fluid screen with the parent being null.
 *
 * @param title - Screens title
 */
public TestScreen(Component title)
{
    super(title);
}

```

## Initialization
The init function runs when the screen is created and is where you add all of your widgets. 
```java
/**
 * This runs when the screen is created.
 */
@Override
protected void init()
{
    // Add widgets here
    //...
    super.init();
}
```

## Render Function
The render function runs every frame, constantly repainting the screen.

```java
/**
 * This runs every frame
 */
@Override
public void render(@NotNull PoseStack poseStack, int mouseX, int mouseY, float partialTicks)
{
    super.render(poseStack, mouseX, mouseY, partialTicks);
}
```

## Conclusion
Making a screen using FluidUI is much simpler that using vanilla minecraft.   
Here is the finalized class.
```java
import chase.minecraft.architectury.fluidui.gui.screen.FluidScreen;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TestScreen extends FluidScreen
{
	/**
	 * Creates a fluid screen
	 *
	 * @param title  - Screens title
	 * @param parent - the parent screen
	 */
	public TestScreen(Component title, @Nullable Screen parent)
	{
		super(title, parent);
	}
	
	/**
	 * Creates a fluid screen with the parent being null.
	 *
	 * @param title - Screens title
	 */
	public TestScreen(Component title)
	{
		super(title);
	}
	
	/**
	 * This runs when the screen is created.
	 */
	@Override
	protected void init()
	{
		// Add widgets here
		//...
		super.init();
	}
	
	/**
	 * This runs every frame
	 */
	@Override
	public void render(@NotNull PoseStack poseStack, int mouseX, int mouseY, float partialTicks)
	{
		super.render(poseStack, mouseX, mouseY, partialTicks);
	}
}

```