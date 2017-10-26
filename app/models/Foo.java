package models;

import io.ebean.Finder;
import io.ebean.Model;
import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;
import org.jsondoc.core.pojo.ApiVisibility;

import java.util.UUID;

@ApiObject(group = "foos", name = "Foo", visibility = ApiVisibility.PUBLIC)
public final class Foo extends Model {

    public static final Finder<UUID, Foo> find = new Finder<>(Foo.class);

    @ApiObjectField(description = "The key of the game", required = true)
    private UUID key;

    @ApiObjectField(description = "The name of the game (e.g. Counter-Strike)", required = true)
    private String name;

    public UUID getKey() {
        return key;
    }

    public void setKey(final UUID key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Foo{" +
                "key=" + key +
                ", name='" + name + '\'' +
                '}';
    }
}
