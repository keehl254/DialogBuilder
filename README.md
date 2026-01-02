Better ReadMe coming soon.

Minecraft API based on Project Unified's UniDialog API. I love their setup, but disliked the need to define a specific Server Type's dialog manager and that every interface under it passed in that specific server types Dialog class. This made it so I would have to write, more or less, the same code twice in two different projects and merge them together if I wanted to support Paper and Spigot.

This version, rather than having conversions in each part of the builder, is ambiguous the entirety of the project. A "DialogAdapter" is passed in at the very last step of constructing the Dialog, and this controls converting all of the API into the actual server types dialog. The DialogAdapater is automatically chosen by DialogManager, so there is no need for developers to specify a Paper or Spigot version.

Not all scenarios have been tested, as I had quickly pumped this out for my Elevators plugin.

Currently available on my repo:

#### Gradle:
```
repositories {
    maven("https://repo.keehl.me/snapshots")
}
```

```
dependencies {
    compileOnly("me.keehl:dialog-builder:1.4-SNAPSHOT")
}
```

#### Maven:
```
<repository>
    <id>keehl-releases</id>
    <name>Keehl Repository</name>
    <url>https://repo.keehl.me/snapshots</url>
</repository>
```

```
<dependency>
    <groupId>me.keehl</groupId>
    <artifactId>dialog-builder</artifactId>
    <version>1.4-SNAPSHOT</version>
</dependency>
```

I recommend shading it into your project at a separate path.
