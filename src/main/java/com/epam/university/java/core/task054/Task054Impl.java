package com.epam.university.java.core.task054;
/*
 * Created by Laptev Egor 17.11.2020
 * */

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

public class Task054Impl implements Task054 {
    @Override
    public BufferedImage grayscaleFilter(String inputFilePath, String outputFilePath) {
        BufferedImage img = null;
        try {
            File input = new File(inputFilePath);
            img = ImageIO.read(input);
            int width = img.getWidth();
            int height = img.getHeight();
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    Color color = new Color(img.getRGB(j, i));
                    int red = (int) (color.getRed() * 0.299);
                    int green = (int) (color.getGreen() * 0.587);
                    int blue = (int) (color.getBlue() * 0.114);
                    int sum = red + green + blue;
                    Color newColor = new Color(sum, sum, sum);
                    img.setRGB(j, i, newColor.getRGB());
                }
            }
            File output = new File(outputFilePath);
            ImageIO.write(img, "jpg", output);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return img;
    }

    @Override
    public BufferedImage sepiaFilter(String inputFilePath, String outputFilePath) {
        BufferedImage img = null;
        try {
            File input = new File(inputFilePath);
            img = ImageIO.read(input);
            int width = img.getWidth();
            int height = img.getHeight();
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    Color col = new Color(img.getRGB(j, i));
                    int red = (int)
                            (col.getRed() * 0.393 + col.getGreen() * 0.769 + col.getBlue() * 0.189);
                    int green = (int)
                            (col.getRed() * 0.349 + col.getGreen() * 0.686 + col.getBlue() * 0.168);
                    int blue = (int)
                            (col.getRed() * 0.272 + col.getGreen() * 0.534 + col.getBlue() * 0.131);
                    if (red > 255) {
                        red = 255;
                    }
                    if (green > 255) {
                        green = 255;
                    }
                    if (blue > 255) {
                        blue = 255;
                    }
                    Color newColor = new Color(red, green, blue);
                    img.setRGB(j, i, newColor.getRGB());
                }
            }
            File output = new File(outputFilePath);
            ImageIO.write(img, "jpg", output);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return img;
    }

    @Override
    public BufferedImage reflectFilter(String inputFilePath, String outputFilePath) {
        BufferedImage resultImg = null;
        try {
            File input = new File(inputFilePath);
            BufferedImage sourceImg = ImageIO.read(input);
            int width = sourceImg.getWidth();
            int height = sourceImg.getHeight();

            resultImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

            for (int i = 0; i < height; i++) {
                for (int lx = 0, rx = width - 1; lx < width; lx++, rx--) {
                    int pixel = sourceImg.getRGB(lx, i);
                    resultImg.setRGB(rx, i, pixel);
                }
            }

            File output = new File(outputFilePath);
            ImageIO.write(resultImg, "jpg", output);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultImg;
    }

    @Override
    public BufferedImage originalImage(String inputFilePath) {
        BufferedImage img = null;
        try {
            File input = new File(inputFilePath);
            img = ImageIO.read(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return img;
    }

    @Override
    public int getRed(int pixel) {
        return (pixel >> 16) & 0xff;
    }

    @Override
    public int getGreen(int pixel) {
        return (pixel >> 8) & 0xff;
    }

    @Override
    public int getBlue(int pixel) {
        return pixel & 0xff;
    }
}
