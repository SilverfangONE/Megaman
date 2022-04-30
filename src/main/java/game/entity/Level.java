package game.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import game.system.Game;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

@Data
public class Level {

    private static Logger logger = LoggerFactory.getLogger(Level.class);
    private  static ObjectMapper objectMapper = new ObjectMapper();

    private final int height, width;
    private int tileHeight, tileWidth;

    private String orientation;
    private String renderOrder;

    private final Layer[] layers;

    @JsonCreator
    public Level(
        @JsonProperty("height") int height,
        @JsonProperty("width") int width,
        @JsonProperty("tileheight") int tileheight,
        @JsonProperty("tilewidth") int tilewidth,
        @JsonProperty("layers") Layer[] layers
    )  {
        this.height = height;
        this.width = width;
        this.tileHeight = tileheight;
        this.tileWidth = tilewidth;
        this.layers = layers;
    }

    /**
     * Converts JSON TileMap File to Level Object by given path
     *
     * @param path location of target JSON Tile Map
     * @return converted Level Object
     * @throws IOException
     */
    public static Level readRaw(String path) throws IOException {

        logger.debug("Try to read level JSON: {}", path);

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream(path);

        String data = null;

        try (Scanner scanner = new Scanner(is, StandardCharsets.UTF_8.name())) {
            data = scanner.useDelimiter("\\A").next();
        }

        return objectMapper.readValue(data, Level.class);
    }

    /**
     * render level tile map layers
     * @param g
     * @return
     */
    public Graphics render( Graphics g ) {

        for(int i = 0; i < this.layers.length; i++) {
            // tile map layer pointer


            int x = 0, y = 0;
            for(int p = 0; p < this.layers[i].getData().length; p++) {
                // default render mode: right-down

                g.drawImage(this.layers[i].getTileSet(), x, y, tileWidth, tileHeight, null);
            }
        }

        return g;
    }
}
