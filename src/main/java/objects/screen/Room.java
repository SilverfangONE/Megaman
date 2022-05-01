package objects.screen;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import objects.PaintableObject;
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
public class Room implements PaintableObject {

    private static Logger logger = LoggerFactory.getLogger(Room.class);
    private static ObjectMapper objectMapper = new ObjectMapper();

    private final int WORLD_HEIGHT;
    private final int WORLD_WIDTH;

    private final TileMap[] layers;

    @JsonCreator
    public Room (
        @JsonProperty("height") int height,
        @JsonProperty("width") int width,
        @JsonProperty("layers") TileMap[] layers
    )  {
        this.WORLD_HEIGHT = height;
        this.WORLD_WIDTH = width;
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

        String data;

        try (Scanner scanner = new Scanner(is, StandardCharsets.UTF_8.name())) {
            data = scanner.useDelimiter("\\A").next();
        }

        return objectMapper.readValue(data, Room.class);
    }

    /**
     * create tileSet from origin img
     *
     * @param g graphics object on which to be drawn
     * @return arrayList with sub buffered images
     */

    @Override
    public void paint ( Graphics g ) {
        for(TileMap tileMap: this.layers) {
            tileMap.paint(g);
        }
    }
}
