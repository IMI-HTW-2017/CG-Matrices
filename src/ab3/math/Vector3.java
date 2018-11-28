package ab3.math;

public class Vector3 {

    private float[] values;

    public Vector3(float x, float y, float z) {
        values = new float[] {x, y, z};
    }

    public float x() {
        return values[0];
    }

    public float y() {
        return values[1];
    }

    public float z() {
        return values[2];
    }

    public float[] getValues() {
        return values;
    }

    public Vector3 normalize() {
        float length = magnitude();
        values[0] /= length;
        values[1] /= length;
        values[2] /= length;
        return this;
    }

    public float magnitude() {
        return (float) Math.sqrt(x() * x() + y() * y() + z() * z());
    }

    public Vector3 cross(Vector3 other) {
        return new Vector3(
                this.values[1] * other.values[2] - this.values[2] * other.values[1],
                this.values[2] * other.values[0] - this.values[0] * other.values[2],
                this.values[0] * other.values[1] - this.values[1] * other.values[0]
        );
    }

}
