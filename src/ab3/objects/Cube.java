package ab3.objects;

import ab3.math.Utilities;
import ab3.math.Vector3;

public class Cube extends Object {

    public Cube(float x, float y, float z, String shader) {
        this(new Vector3(x, y, z), Vector3.zero(), Vector3.one(), shader);
    }

    public Cube(Vector3 position, Vector3 rotation, Vector3 scale, String shader) {
        super(position, rotation, scale, shader);

        vertices = new float[] {
                //front
                -0.5F, -0.5F, 0.5F,
                0.5F, -0.5F, 0.5F,
                -0.5F, 0.5F, 0.5F,

                -0.5F, 0.5F, 0.5F,
                0.5F, -0.5F, 0.5F,
                0.5F, 0.5F, 0.5F,

                //back
                -0.5F, -0.5F, -0.5F,
                -0.5F, 0.5F, -0.5F,
                0.5F, -0.5F, -0.5F,

                -0.5F, 0.5F, -0.5F,
                0.5F, 0.5F, -0.5F,
                0.5F, -0.5F, -0.5F,

                //botton
                -0.5F, -0.5F, 0.5F,
                0.5F, -0.5F, -0.5F,
                0.5F, -0.5F, 0.5F,

                -0.5F, -0.5F, 0.5F,
                -0.5F, -0.5F, -0.5F,
                0.5F, -0.5F, -0.5F,

                //top
                -0.5F, 0.5F, 0.5F,
                0.5F, 0.5F, 0.5F,
                0.5F, 0.5F, -0.5F,

                -0.5F, 0.5F, 0.5F,
                0.5F, 0.5F, -0.5F,
                -0.5F, 0.5F, -0.5F,

                //left
                -0.5F, 0.5F, 0.5F,
                -0.5F, 0.5F, -0.5F,
                -0.5F, -0.5F, 0.5F,

                -0.5F, -0.5F, 0.5F,
                -0.5F, 0.5F, -0.5F,
                -0.5F, -0.5F, -0.5F,

                //right
                0.5F, 0.5F, 0.5F,
                0.5F, -0.5F, 0.5F,
                0.5F, 0.5F, -0.5F,

                0.5F, -0.5F, 0.5F,
                0.5F, -0.5F, -0.5F,
                0.5F, 0.5F, -0.5F
        };

        normals = Utilities.calculateObjectNormals(vertices);

        uvs = new float[] {
                //front
                0, 1,
                1, 1,
                0, 0,

                0, 0,
                1, 1,
                1, 0,

                //back
                1, 1,
                1, 0,
                0, 1,

                1, 0,
                0, 0,
                0, 1,

                //bottom
                0, 0,
                1, 1,
                1, 0,

                0, 0,
                0, 1,
                1, 1,

                //top
                0, 1,
                1, 1,
                1, 0,

                0, 1,
                1, 0,
                0, 0,

                //left
                1, 0,
                0, 0,
                1, 1,

                1, 1,
                0, 0,
                0, 1,

                //right
                0, 0,
                0, 1,
                1, 0,

                0, 1,
                1, 1,
                1, 0
        };

        super.init();
    }
}
