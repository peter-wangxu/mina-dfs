package demo.image_process;

/**
 * Created by peter on 14-12-2.
 */
public class ImageRequest {

    private int width;
    private int height;
    private int numberOfCharacters;

    public ImageRequest(int width, int height, int numberOfCharacters) {
        this.width = width;
        this.height = height;
        this.numberOfCharacters = numberOfCharacters;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getNumberOfCharacters() {
        return numberOfCharacters;
    }
}
