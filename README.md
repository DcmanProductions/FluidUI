![Fluid UI Banner.svg](docs/images/Fluid%20UI%20Banner.svg)

# What is FluidUI

FluidUI is a minecraft user interface library. This helps minecraft mod developers easily create a user interface in
minecraft.   
This mod is built with no modding platform in mind. Meaning it can be integrated in
**_[Forge](https://files.minecraftforge.net)_**,
**_[Fabric](https://fabricmc.net/develop)_**,
**_[Quilt](https://quiltmc.org)_**,
and any other minecraft modding platforms that popup throughout the years.

# Table of Contents
- [Installation](#installation)
- [Themes](https://dcmanproductions.github.io/FluidUI/themes)
- [Screen](https://dcmanproductions.github.io/FluidUI/screen)
- [Layouts](https://dcmanproductions.github.io/FluidUI/layouts/)
- [Widgets](https://dcmanproductions.github.io/FluidUI/widgets/)

# Installation

Add the [Modrith Maven](https://docs.modrinth.com/docs/tutorials/maven/) to your `build.gradle` file

```groovy
repositories {
    // Add the modrinth maven
    maven {
        url = "https://api.modrinth.com/maven"
    }
}
```

Then add FluidUI to your dependencies. You can find the right version on
our [Modrinth](https://modrinth.com/mod/fluidui) page

## Common

Add the version to your `gradle.properties` files

```properties
#FluidUI Version
fluidui=0.0.1 #put your version here
```

## Fabric, Quilt, Architectury

```groovy
dependencies {
    // Adding a FluidUI dependency for fabric, quilt and architectury
    modImplementation include("maven.modrinth:fluidui:${project.fluidui}")
}
```

## Forge

```groovy
dependencies {
    // Adding a FluidUI dependency for forge
    implementation fg.deobf("maven.modrinth:fluidui:${project.fluidui}")
}
```