import java.io.File;

public class ImageType
{

    private static String       imageFullPath;
    private static final String IMAGE_EXT_JPG = "jpg";
    private static final String IMAGE_EXT_JPEG = "jpeg";
    private static final String IMAGE_EXT_PNG = "png";
    private static final String IMAGE_EXT_GIF = "gif";

    /* constant string to hold allowed image types */
    public static final String IMAGE_ALLOW_TYPES = "Image types allowed - " + IMAGE_EXT_JPG + IMAGE_EXT_JPEG
            + IMAGE_EXT_PNG + IMAGE_EXT_GIF;

    /* constructor to set the full path of the image */
    public ImageType(String imageFullPath)
    {
        this.imageFullPath = imageFullPath;
    }

    /* checks if the image file is of the allowed type */
    public boolean isFileAValidImage()
    {
        if (imageFullPath == null) 
            throw new NullPointerException("Image full path cannot be null or empty");

        File    imageFile = new File(imageFullPath);
        String  ext = getFileExtension(imageFile);

        if (IMAGE_EXT_GIF.equalsIgnoreCase(ext) || IMAGE_EXT_JPEG.equalsIgnoreCase(ext)
                || IMAGE_EXT_JPG.equalsIgnoreCase(ext) || IMAGE_EXT_PNG.equalsIgnoreCase(ext))
            return (true);
        return (false);
    }

    /* helper method to get the extension of the file */
    private static String getFileExtension(File imageFile)
    {
        if (imageFile == null)
            throw new NullPointerException("Image file cannot be null");

        String  name = imageFile.getName();
        int     lastDotIndex = name.lastIndexOf(".");

        if (lastDotIndex > 0 && lastDotIndex < (name.length() - 1))
            /* return the extension in lowercase */
            return (name.substring(lastDotIndex + 1).toLowerCase());
        /* return an empty string if no extension is found */
        return ("");
    }
}