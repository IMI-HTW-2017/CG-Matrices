package ab3.math;

//Alle Operationen ändern das Matrixobjekt selbst und geben das eigene Matrixobjekt zurück
//Dadurch kann man Aufrufe verketten, z.B.
//Matrix4 m = new Matrix4().scale(5).translate(0,1,0).rotateX(0.5f);
public class Matrix4 {

	private float[] values;

	public Matrix4() {
		values = new float[] {
		        1, 0, 0, 0,
                0, 1, 0, 0,
                0, 0, 1, 0,
                0, 0, 0, 1
        };
	}

    public Matrix4(Matrix4 copy) {
		values = copy.values.clone();
	}

    public Matrix4(float near, float far) {
		values = new float[] {
		        near, 0, 0, 0,
                0, near, 0, 0,
                0, 0, (-far - near) / (far - near), -1,
                0, 0, (-2 * near * far) / (far - near), 0
        };
	}

    public Matrix4 multiply(Matrix4 other) {
        float[] newValues = new float[16];

        newValues[0] = other.values[0] * values[0] + other.values[4] * values[1] + other.values[8] * values[2] + other.values[12] * values[3];
        newValues[1] = other.values[1] * values[0] + other.values[5] * values[1] + other.values[9] * values[2] + other.values[13] * values[3];
        newValues[2] = other.values[2] * values[0] + other.values[6] * values[1] + other.values[10] * values[2] + other.values[14] * values[3];
        newValues[3] = other.values[3] * values[0] + other.values[7] * values[1] + other.values[11] * values[2] + other.values[15] * values[3];

        newValues[4] = other.values[0] * values[4] + other.values[4] * values[5] + other.values[8] * values[6] + other.values[12] * values[7];
        newValues[5] = other.values[1] * values[4] + other.values[5] * values[5] + other.values[9] * values[6] + other.values[13] * values[7];
        newValues[6] = other.values[2] * values[4] + other.values[6] * values[5] + other.values[10] * values[6] + other.values[14] * values[7];
        newValues[7] = other.values[3] * values[4] + other.values[7] * values[5] + other.values[11] * values[6] + other.values[15] * values[7];

        newValues[8] = other.values[0] * values[8] + other.values[4] * values[9] + other.values[8] * values[10] + other.values[12] * values[11];
        newValues[9] = other.values[1] * values[8] + other.values[5] * values[9] + other.values[9] * values[10] + other.values[13] * values[11];
        newValues[10] = other.values[2] * values[8] + other.values[6] * values[9] + other.values[10] * values[10] + other.values[14] * values[11];
        newValues[11] = other.values[3] * values[8] + other.values[7] * values[9] + other.values[11] * values[10] + other.values[15] * values[11];

        newValues[12] = other.values[0] * values[12] + other.values[4] * values[13] + other.values[8] * values[14] + other.values[12] * values[15];
        newValues[13] = other.values[1] * values[12] + other.values[5] * values[13] + other.values[9] * values[14] + other.values[13] * values[15];
        newValues[14] = other.values[2] * values[12] + other.values[6] * values[13] + other.values[10] * values[14] + other.values[14] * values[15];
        newValues[15] = other.values[3] * values[12] + other.values[7] * values[13] + other.values[11] * values[14] + other.values[15] * values[15];

        values = newValues;

		return this;
	}

    public Matrix4 translate(float x, float y, float z) {
		values[0] = values[0] + x * values[3];
        values[1] = values[1] + y * values[3];
        values[2] = values[2] + z * values[3];

        values[4] = values[4] + x * values[7];
        values[5] = values[5] + y * values[7];
        values[6] = values[6] + z * values[7];

        values[8] = values[8] + x * values[11];
        values[9] = values[9] + y * values[11];
        values[10] = values[10] + z * values[11];

        values[12] = values[12] + x * values[15];
        values[13] = values[13] + y * values[15];
        values[14] = values[14] + z * values[15];

		return this;
	}

    public Matrix4 scale(float uniformFactor) {
        return scale(uniformFactor, uniformFactor, uniformFactor);
	}

    public Matrix4 scale(float sx, float sy, float sz) {
        values[0] *= sx;
        values[1] *= sy;
        values[2] *= sz;

        values[4] *= sx;
        values[5] *= sy;
        values[6] *= sz;

        values[8] *= sx;
        values[9] *= sy;
        values[10] *= sz;

        values[12] *= sx;
        values[13] *= sy;
        values[14] *= sz;

		return this;
	}

    public Matrix4 rotateX(float angle) {
        double radAngle = Math.toRadians(angle);

        float[] newValues = new float[16];

		newValues[0] = values[0];
		newValues[1] = (float) Math.cos(radAngle) * values[1] + (float) -Math.sin(radAngle) * values[2];
		newValues[2] = (float) Math.sin(radAngle) * values[1] + (float) Math.cos(radAngle) * values[2];
		newValues[3] = values[3];

        newValues[4] = values[4];
        newValues[5] = (float) Math.cos(radAngle) * values[5] + (float) -Math.sin(radAngle) * values[6];
        newValues[6] = (float) Math.sin(radAngle) * values[5] + (float) Math.cos(radAngle) * values[6];
        newValues[7] = values[7];

        newValues[8] = values[8];
        newValues[9] = (float) Math.cos(radAngle) * values[9] + (float) -Math.sin(radAngle) * values[10];
        newValues[10] = (float) Math.sin(radAngle) * values[9] + (float) Math.cos(radAngle) * values[10];
        newValues[11] = values[11];

        newValues[12] = values[12];
        newValues[13] = (float) Math.cos(radAngle) * values[13] + (float) -Math.sin(radAngle) * values[14];
        newValues[14] = (float) Math.sin(radAngle) * values[13] + (float) Math.cos(radAngle) * values[14];
        newValues[15] = values[15];

        values = newValues;

		return this;
	}

    public Matrix4 rotateY(float angle) {
	    double radAngle = Math.toRadians(angle);

        float[] newValues = new float[16];

        newValues[0] = (float) Math.cos(radAngle) * values[0] + (float) -Math.sin(radAngle) * values[2];
        newValues[1] = values[1];
        newValues[2] = (float) Math.sin(radAngle) * values[0] + (float) Math.cos(radAngle) * values[2];
        newValues[3] = values[3];

        newValues[4] = (float) Math.cos(radAngle) * values[4] + (float) -Math.sin(radAngle) * values[6];
        newValues[5] = values[5];
        newValues[6] = (float) Math.sin(radAngle) * values[4] + (float) Math.cos(radAngle) * values[6];
        newValues[7] = values[7];

        newValues[8] = (float) Math.cos(radAngle) * values[8] + (float) -Math.sin(radAngle) * values[10];
        newValues[9] = values[9];
        newValues[10] = (float) Math.sin(radAngle) * values[8] + (float) Math.cos(radAngle) * values[10];
        newValues[11] = values[11];

        newValues[12] = (float) Math.cos(radAngle) * values[12] + (float) -Math.sin(radAngle) * values[14];
        newValues[13] = values[13];
        newValues[14] = (float) Math.sin(radAngle) * values[12] + (float) Math.cos(radAngle) * values[14];
        newValues[15] = values[15];

        values = newValues;

		return this;
	}

    public Matrix4 rotateZ(float angle) {
        double radAngle = Math.toRadians(angle);

        float[] newValues = new float[16];

        newValues[0] = (float) Math.cos(radAngle) * values[0] + (float) -Math.sin(radAngle) * values[1];
        newValues[1] = (float) Math.sin(radAngle) * values[0] + (float) Math.cos(radAngle) * values[1];
        newValues[2] = values[2];
        newValues[3] = values[3];

        newValues[4] = (float) Math.cos(radAngle) * values[4] + (float) -Math.sin(radAngle) * values[5];
        newValues[5] = (float) Math.sin(radAngle) * values[4] + (float) Math.cos(radAngle) * values[5];
        newValues[6] = values[6];
        newValues[7] = values[7];

        newValues[8] = (float) Math.cos(radAngle) * values[8] + (float) -Math.sin(radAngle) * values[9];
        newValues[9] = (float) Math.sin(radAngle) * values[8] + (float) Math.cos(radAngle) * values[9];
        newValues[10] = values[10];
        newValues[11] = values[11];

        newValues[12] = (float) Math.cos(radAngle) * values[12] + (float) -Math.sin(radAngle) * values[13];
        newValues[13] = (float) Math.sin(radAngle) * values[12] + (float) Math.cos(radAngle) * values[13];
        newValues[14] = values[14];
        newValues[15] = values[15];

        values = newValues;

		return this;
	}

    public float[] getValuesAsArray() {
		return values;
	}
}
