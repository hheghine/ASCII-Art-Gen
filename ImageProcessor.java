import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.ImagingOpException;
import java.io.File;

public class ImageProcessor
{

    private ImageType   imageType;
    private String      imagePath;

    /* constructor which sets the image path and creates an instance of ImageType */
    public ImageProcessor(String imageFullPath)
    {
        imagePath = imageFullPath;
        imageType = new ImageType(imagePath);
    }

    /* the brightness matrix of the image */
    public int[][] getBrightnessMatrix()
    {
        BufferedImage   image = getImage(imagePath);
        int[][]         pixels = getPixelsFromImage(image);
        return (setBrightnessMatrix(pixels));
    }

    /* the BufferedImage from the given path */
    private BufferedImage getImage(String imageFullPath)
    {
        BufferedImage bufferedImage = null;
        try
        {
            if (imageFullPath == null)
                throw new NullPointerException("Image full path cannot be null or empty");
            boolean isImage = imageType.isFileAValidImage();
            if (!isImage)
                throw new ImagingOpException(ImageType.IMAGE_ALLOW_TYPES);
            String tempImagePath = imageFullPath;
            bufferedImage = ImageIO.read(new File(tempImagePath));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return (bufferedImage);
    }

    /* making a 2D int array of image pixels */
    private static int[][] getPixelsFromImage(BufferedImage bufferedImage) /* BufferedImage object */
    {
        if (bufferedImage == null)
            throw new IllegalArgumentException();
        int     h = bufferedImage.getHeight();
        int     w = bufferedImage.getWidth();
        int[][] pixels = new int[h][w];
        for (int i = 0; i < w; ++i)
        {
            for (int j = 0; j < h; ++j)
                pixels[j][i] = bufferedImage.getRGB(i, j);
        }
        return (pixels);
    }

    /* calculating the brightness matrix from the given 2D int array of pixels */
     private static int[][] setBrightnessMatrix(int[][] pixels) /* 2D int array of pixels */
     {
        int     r, g, b;
        int     average;
        int[][] avg = new int[pixels.length][pixels[0].length];

        /* iterating through the 2D int array of pixels */
        for (int i = 0; i < pixels.length; ++i)
        {
            for (int j = 0; j < pixels[0].length; ++j)
            {
            /* extracting the red, green and blue values of the current pixel */
                r = (pixels[i][j]>>16) & 0xff ;
                g = (pixels[i][j]>>8) & 0xff ;
                b = (pixels[i][j]) & 0xff ;
            /* average brightness of the current pixel */
                average=(r+g+b)/3;
            /* brightness matrix */
              avg[i][j]=average;
            }
        }
        return (avg);
    }
}