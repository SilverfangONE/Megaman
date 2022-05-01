package objects.screen;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Layer {

    private int id;
    private String name;
    private String type;
    private String img_src;
    private int[] data;
    private int height;
    private int width;
    private int y;
    private int x;
    private int opacity;

    @JsonCreator
    public Layer(
            @JsonProperty ("id") int id,
            @JsonProperty ("name") String name,
            @JsonProperty ("type") String type,
            @JsonProperty ("width") int width,
            @JsonProperty ("height") int height,
            @JsonProperty ("img_src") String img_src,
            @JsonProperty ("x") int x,
            @JsonProperty ("y") int y,
            @JsonProperty ("data") int[] data,
            @JsonProperty ("opacity") int opacity
    ) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.width = width;
        this.height = height;
        this.img_src = img_src;
        this.x = x;
        this.y = y;
        this.data = data;
        this.opacity = opacity;
    }
}
