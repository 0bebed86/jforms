package jgui;

public enum TextAlignment {
    NONE(0.0f), LEFT(-1.0f), MIDDLE(-0.5f), RIGHT(1.0f);

    public float value;

    private TextAlignment(float value){
        this.value = value;
    }

/*
x = 4;
width = 4;

NONE:           a b c d             x = 4
LEFT:   a b c d                     x = 0
MIDDLE:     a b c d                 x = 2
RIGHT:                  a b c d     x = 8
        ^ ^ ^ ^ ^ ^ ^ ^ ^ ^ ^ ^
        0 1 2 3 4 5 6 7 8 9 10
*/
    public int align(int x, int width){
        if(this != NONE){
            x += (int)((float)width * value);
        }

        return x;
    }

    /*public int alignInBounds(int left, int begin, int end, int right){
        int width = right - left; //width of holder
        int length = end - begin; //length of text
        
        int offset = begin - left; //empty space

        return begin; //todo
    }*/
}
