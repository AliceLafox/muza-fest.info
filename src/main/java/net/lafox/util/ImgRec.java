package net.lafox.util;

public class ImgRec {

    private int width;
    private int height;

    public ImgRec(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public ImgRec(double width, double height) {
        this.width = (int)Math.round(width);
        this.height = (int)Math.round(height);
    }

    public int getWidth() {
        return width;
    }


    public int getHeight() {
        return height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImgRec imgRec = (ImgRec) o;
        return width == imgRec.width && height == imgRec.height;
    }

    @Override
    public int hashCode() {
        int result = width;
        result = 31 * result + height;
        return result;
    }

    @Override
    public String toString() {
        return "ImgRec{" +
                "width=" + width +
                ", height=" + height +
                '}';
    }

}
