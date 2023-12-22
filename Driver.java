public class Driver
{
    public static void main(String[] args)
    {
        /* Increase the scale down factor (e.g., 4, 5, etc.) for smaller ASCII art */
        int scaleDownFactor = 5;

        ImageToAscii asciiImage = new ImageToAscii(args[0]);
        
        /* Print the ASCII art with the specified scale down factor */
        asciiImage.printAsciiMatrix(scaleDownFactor);
    }
}

