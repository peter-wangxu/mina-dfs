package demo.image_process;

import java.awt.image.BufferedImage;

/**
 * Created by peter on 14-12-2.
 */
public class ImageResponse {

    private BufferedImage image1;

    private BufferedImage image2;

    public ImageResponse(BufferedImage image1, BufferedImage image2) {
        this.image1 = image1;
        this.image2 = image2;
    }

    public BufferedImage getImage1() {
        return image1;
    }

    public BufferedImage getImage2() {
        return image2;
    }
}
