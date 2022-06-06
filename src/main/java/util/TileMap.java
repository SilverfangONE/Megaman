package util;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Layer from HoleTileMap
 */
@Data
@NoArgsConstructor
public class TileMap {

    private int id;
    private String name;
    private String type;
    private String imgPath;
    private int[] data;
    private int height;
    private int width;
    private int y;
    private int x;
    private int opacity;
    private ArrayList<BufferedImage> bufferedImages;

    @JsonCreator
    public TileMap (
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
        this.imgPath = img_src;
        this.x = x;
        this.y = y;
        this.data = data;
        this.opacity = opacity;
        this.bufferedImages = createTileSet();
    }

    /**
     * Create tile set from buffered image
     * @return tile set as buffered image list
     */
    public ArrayList<BufferedImage> createTileSet () {

        BufferedImage source = loadImg(this.getImgPath());

        ArrayList<BufferedImage> tileMap = new ArrayList<>();

        // iterate over image to create tile set
        for (int y = 0; y < source.getHeight(); y += PropertiesLoader.getInt("game.tile.height")) {
            for (int x = 0; x < source.getWidth(); x += PropertiesLoader.getInt("game.resolution.width")) {
                tileMap.add(source.getSubimage(x, y, PropertiesLoader.getInt("game.resolution.width"), PropertiesLoader.getInt("game.tile.height")));
            }
        }
        return tileMap;
    }

    /**
     * load img from classpath
     * @param path from target img
     * @return target buffered img
     */
    public BufferedImage loadImg ( String path ) {
        try {
            return ImageIO.read(getClass().getResource("/sprite/tile/bricktileset16x16.png"));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public void paint ( Graphics g ) {

        // tile map layer pointer
        final int TILE_HEIGHT = PropertiesLoader.getInt("game.tile.height");
        final int TILE_WIDTH = PropertiesLoader.getInt("game.tile.width");

        int y = 0, x = 0, count = 0;
        for (int p = 0; p < this.data.length; p++) {
            // default render mode: right-down

            if (count > width) {
                count = 0;
                y += TILE_HEIGHT;
                x = 0;
            }

            if (data[p] > 0) {
               /* g.drawImage(
                        bufferedImages.get(this.data[p] - 1),
                        x,
                        y,
                        16,
                        16,
                        null

                );
                */
            }

            // increment
            x += TILE_WIDTH;
            count++;
        }
    }
}
