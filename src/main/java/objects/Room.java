package objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import util.TileMap;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

@Data
@Slf4j
public class Room {

    private static ObjectMapper objectMapper = new ObjectMapper();

    private final int roomHeight;
    private final int roomWidth;

    private final TileMap[] layers;

    @JsonCreator
    public Room (
        @JsonProperty("height") int height,
        @JsonProperty("width") int width,
        @JsonProperty("layers") TileMap[] layers
    )  {
        this.roomHeight = height;
        this.roomWidth = width;
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

        log.debug("Try to read level JSON: {}", path);

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
    public void paint ( Graphics g ) {
        for(TileMap tileMap: this.layers) {
            tileMap.paint(g);
        }
    }
}
