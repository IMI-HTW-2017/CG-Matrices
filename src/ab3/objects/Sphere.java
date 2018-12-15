package ab3.objects;

import ab3.math.Utilities;
import ab3.math.Vector3;

public class Sphere extends Object {

	public Sphere(float x, float y, float z, String shader) {
		this(new Vector3(x, y, z), Vector3.zero(), Vector3.one(), shader);
	}

    public Sphere(Vector3 position, Vector3 rotation, Vector3 scale, String shader) {
    	super(position, rotation, scale, shader);

        float val = (float) (Math.sqrt(3) / 4);

		vertices = new float[] {
				//front
				0.5f, val, 0,
				-0.5f, val, 0,
				0, -val, 0.5f,

				//left
				-0.5f, val, 0,
				0, -val, -0.5f,
				0, -val, 0.5f,

				//right
				0.5f, val, 0,
				0, -val, 0.5f,
				0, -val, -0.5f,

				//back
				-0.5f, val, 0,
				0.5f, val, 0,
				0, -val, -0.5f
		};

		normals = Utilities.calculateObjectNormals(vertices);

		super.init();
    }
}
