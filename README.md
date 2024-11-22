# gradlebukkitplugin
Um plugin gradle para a criação de plugins bukkit/spigot

## Instalação
Aplique o plugin no seu `build.gradle`
```groovy
plugins {
    id 'gradlebukkit' version '1.0.0'
}
```

## Criando plugin.yml pelo build.gradle
Use a extensão `bukkitplugin` em qualquer build.gradle que esteja com a aplicação do plugin

```groovy
import com.github.imhammer.gradlebukkit.GradlebukkitExtension.LoadType
import com.github.imhammer.gradlebukkit.GradlebukkitExtension.DefaultType

bukkitpulugin {
    main = "org.spigotmc.annotationtest.Test"
    name = "TestPlugin"
    version = "1.0"
    description = "A test plugin"
    load = LoadType.POSTWORLD
    authors = ['md_5']
    website = "spigotmc.org"
    prefix = "Testing"
    depends = ['WorldEdit', 'Towny']
    softDepends = ['FAWE']
    loadBefore = ['Essentials']
    
    command {
        name = "foo"
        description = "Foo Command"
        aliases = ['foobar', 'fubar']
        permission = "test.foo"
        permissionMessage = "You do not have permission!"
        usage = "/<command> [test|stop]"
    }

    permission {
        name = "test.foo"
        description = "Allows foo command"
        defType = DefaultType.OP
    }

    permission {
        name = "test.*"
        description = "Wildcard permission"
        defType = DefaultType.OP
    }

    permissionChild {
        parent = "test.*"
        name = "test.foo"
        defType = DefaultType.TRUE
    }
}
```

### Output plugin.yml
```yaml
main: org.spigotmc.annotationtest.Test
name: TestPlugin
version: '1.0'
description: A test plugin
load: POSTWORLD
author: md_5
depend:
- WorldEdit
- Towny
prefix: Testing
softdepend:
- FAWE
loadbefore:
- Essentials
commands:
  foo:
    description: Foo Command
    aliases:
    - foobar
    - fubar
    permission: test.foo
    permission-message: You do not have permission!
    usage: /<command> [test|stop]
permissions:
  test.foo:
    default: op
    description: Allows foo command
  test.*:
    default: op
    description: Wildcard permission
    children:
      test.foo: true

```