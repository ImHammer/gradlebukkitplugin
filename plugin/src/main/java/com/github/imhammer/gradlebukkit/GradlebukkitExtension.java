package com.github.imhammer.gradlebukkit;

import org.gradle.api.Action;
import org.gradle.api.model.ObjectFactory;
import org.gradle.api.provider.ListProperty;
import org.gradle.api.provider.Property;

// interface IGradlebukkitExtension
// {
//     Property<String> getName();
//     Property<String> getVersion();
//     Property<String> getMain();
//     Property<String> getDescription();
//     ListProperty<String> getAuthors();
// }

class GradlebukkitExtension
{
    public static interface Command
    {
        Property<String> getName();

        Property<String> getDescription();

        ListProperty<String> getAliases();

        Property<String> getPermission();

        Property<String> getPermissionMessage();

        Property<String> getUsage();
    }

    public static interface Permission
    {
        Property<String> getName();

        Property<String> getDescription();

        Property<DefaultType> getDefType();
    }

    public static interface PermissionChild
    {
        Property<String> getParent();
        
        Property<String> getName();

        Property<DefaultType> getDefType();
    }

    public static enum LoadType
    {
        STARTUP, POSTWORLD
    }

    public static enum DefaultType
    {
        TRUE, FALSE, OP, NOT_OP;

        public Object toRaw()
        {
            switch(this) {
                case TRUE: return true;
                case FALSE: return false;
                default: return this.name().toLowerCase();
            }
        }
    }

    private final ObjectFactory factory;

    Property<String> main;
    Property<String> name;
    Property<String> version;
    Property<String> description;
    Property<String> apiVersion;
    Property<LoadType> load;
    ListProperty<String> authors;
    Property<String> website;
    ListProperty<String> depends;
    Property<String> prefix;
    ListProperty<String> softDepends;
    ListProperty<String> loadBefore;
    ListProperty<String> libraries;

    ListProperty<Command> commands;
    ListProperty<Permission> permissions;
    ListProperty<PermissionChild> permissionsChildren;

    public GradlebukkitExtension(ObjectFactory objectFactory)
    {
        this.main = objectFactory.property(String.class);
        this.name = objectFactory.property(String.class);
        this.version = objectFactory.property(String.class);
        this.description = objectFactory.property(String.class);
        this.apiVersion = objectFactory.property(String.class);
        this.load = objectFactory.property(LoadType.class);
        this.authors = objectFactory.listProperty(String.class);
        this.website = objectFactory.property(String.class);
        this.depends = objectFactory.listProperty(String.class);
        this.softDepends = objectFactory.listProperty(String.class);
        this.prefix = objectFactory.property(String.class);
        this.loadBefore = objectFactory.listProperty(String.class);
        this.libraries = objectFactory.listProperty(String.class);

        this.commands = objectFactory.listProperty(Command.class);
        this.permissions = objectFactory.listProperty(Permission.class);
        this.permissionsChildren = objectFactory.listProperty(PermissionChild.class);

        this.factory = objectFactory;
    }

    public void command(Action<? super Command> action)
    {
        Command command = factory.newInstance(Command.class);
        action.execute(command);
        commands.add(command);
    }

    public void permission(Action<? super Permission> action)
    {
        Permission permission = factory.newInstance(Permission.class);
        action.execute(permission);
        permissions.add(permission);
    }

    public void permissionChild(Action<? super PermissionChild> action)
    {
        PermissionChild permissionChild = factory.newInstance(PermissionChild.class);
        action.execute(permissionChild);
        permissionsChildren.add(permissionChild);
    }

    public ObjectFactory getFactory()
    {
        return factory;
    }

    public Property<String> getMain()
    {
        return main;
    }

    public Property<String> getName()
    {
        return name;
    }

    public Property<String> getVersion()
    {
        return version;
    }

    public Property<String> getDescription()
    {
        return description;
    }

    public Property<String> getApiVersion()
    {
        return apiVersion;
    }

    public Property<LoadType> getLoad()
    {
        return load;
    }

    public void setLoad(String load)
    {
        this.load.set(LoadType.valueOf(load.toUpperCase()));
    }

    public ListProperty<String> getAuthors()
    {
        return authors;
    }

    public Property<String> getWebsite()
    {
        return website;
    }

    public ListProperty<String> getDepends()
    {
        return depends;
    }

    public ListProperty<String> getSoftDepends()
    {
        return softDepends;
    }

    public Property<String> getPrefix()
    {
        return prefix;
    }

    public ListProperty<String> getLoadBefore()
    {
        return loadBefore;
    }

    public ListProperty<String> getLibraries()
    {
        return libraries;
    }

    public ListProperty<Command> getCommands()
    {
        return commands;
    }

    public ListProperty<Permission> getPermissions()
    {
        return permissions;
    }
    
    public ListProperty<PermissionChild> getPermissionsChildren()
    {
        return permissionsChildren;
    }
}
