package objects.screen;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

@Data
public class Room {

    private static Logger logger = LoggerFactory.getLogger(Room.class);
    private static ObjectMapper objectMapper = new ObjectMapper();

    private final int WORLD_HEIGHT;
    private final int WORLD_WIDTH;

    private final int TILE_HEIGHT;
    private final int TILE_WIDTH;

    private final Layer[] layers;

    @JsonCreator
    public Room (
        @JsonProperty("height") int height,
        @JsonProperty("width") int width,
        @JsonProperty("tileheight") int tileheight,
        @JsonProperty("tilewidth") int tilewidth,
        @JsonProperty("layers") Layer[] layers
    )  {
        this.WORLD_HEIGHT = height;
        this.WORLD_WIDTH = width;
        this.TILE_HEIGHT = tileheight;
        this.TILE_WIDTH = tilewidth;
        this.layers = layers;
    }

    /**
     * Converts JSON TileMap File to Level Object by given path
     *
     * @param path location of target JSON Tile Map
     * @return converted Level Object
     * @throws IOException
     */
    public static Room readRaw( String path) throws IOException {

        logger.debug("Try to read level JSON: {}", path);

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream(path);

        String data = null;

        try (Scanner scanner = new Scanner(is, StandardCharsets.UTF_8.name())) {
            data = scanner.useDelimiter("\\A").next();
        }

        return objectMapper.readValue(data, Room.class);
    }

    /**
     * render level tile map layers
     * @param g
     * @return
     */
    public Graphics render( Graphics g ) {
        for(int i = 0; i < this.layers.length; i++) {
            // tile map layer pointer

            ArrayList<BufferedImage> tileSet = createTileSetFromImg(this.layers[i]);

            int y = 0, x = 0, count = 0;
            for(int p = 0; p < this.layers[i].getData().length; p++) {
                // default render mode: right-down

                if (count > this.layers[i].getWidth()) {
                    count = 0;
                    y += this.tileHeight;
                    x = 0;
                }

                if(this.layers[i].getData()[p] > 0) {
                    g.drawImage(
                            tileSet.get(this.layers[i].getData()[p] - 1 ),
                            x,
                            y,
                            16,
                            16,
                            null
                    );
                }

                // increment
                x += this.tileWidth;
                count++;
            }
        }

        return g;
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

    /**
     * create tileSet from origin img
     *
     * @param layer
     * @return arrayList with sub buffered images
     */
    public ArrayList<BufferedImage> createTileSetFromImg ( Layer layer ) {
        BufferedImage source = loadImg(layer.getImg_src());
        ArrayList<BufferedImage> tileMap = new ArrayList<>();
        for (int y = 0; y < source.getHeight(); y += this.tileHeight) {
            for (int x = 0; x < source.getWidth(); x += this.tileWidth) {
                tileMap.add(source.getSubimage(x, y, this.tileWidth, this.tileHeight));
            }
        }
        return tileMap;
    }
}
