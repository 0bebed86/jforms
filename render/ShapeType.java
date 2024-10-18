package jforms.render;

public enum ShapeType {
    TRIANGLE(0), QUAD(1);

    public int value;

    private ShapeType(int value) {
        this.value = value;
    }
}
