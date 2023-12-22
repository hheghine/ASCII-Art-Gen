/* class to convert an image to ASCII characters */
public class ImageToAscii
{

    private ImageProcessor      processor;
    private static final String ASCIICHARS = "`^\",:;Il!i~+_-?][}{1)|\\/tfjrxnuvczXYUJCLQ0OZmwqpdbkhao*#MW&8%B@$";

    public ImageToAscii(String imageFullPath)
    {
        processor = new ImageProcessor(imageFullPath);
    }

    public void printAsciiMatrix(int scaleDown)
    {
        char[][]    pixels = setAsciiMatrix(processor.getBrightnessMatrix(), scaleDown);

        for (char[] row : pixels)
        {
            for (char pixel : row)
                System.out.print(pixel);
            System.out.println();
        }
    }

    private static char[][] setAsciiMatrix(int[][] brightnessMatrix, int scaleDown)
    {
        int         height = brightnessMatrix.length / scaleDown;
        int         width = brightnessMatrix[0].length / scaleDown;
        char[][]    asciiMatrix = new char[height][width];
        int         sum;
        int         count;
        int         averageBrightness;

        for (int i = 0; i < height; i++)
        {
            for (int j = 0; j < width; j++)
            {
                sum = 0;
                count = 0;
                for (int k = i * scaleDown; k < (i + 1) * scaleDown; k++)
                {
                    for (int l = j * scaleDown; l < (j + 1) * scaleDown; l++)
                    {
                        sum += brightnessMatrix[k][l];
                        count++;
                    }
                }
                averageBrightness = sum / count;
                asciiMatrix[i][j] = convertToAscii(averageBrightness);
            }
        }
        return (asciiMatrix);
    }

    private static char convertToAscii(int brightnessValue)
    {
        int asciiIndex = (int) ((ASCIICHARS.length() - 1) * (brightnessValue / 255.0));
        return (ASCIICHARS.charAt(asciiIndex));
    }
}